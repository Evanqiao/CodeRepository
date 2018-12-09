package dal.impl;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dal.api.QueryStockDataService;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public class QueryStockDataServiceWithWebApi implements QueryStockDataService {

    private Gson gson;

    private final static String FILE_NAME_PREFIX = "stock-stock/res/";
    private final static String FILE_NAME_SUFFIX = ".txt";

    public QueryStockDataServiceWithWebApi() {
        gson = new GsonBuilder().create();
    }

    @Override
    public Map<String, Double> queryAllStockPricesByName(String name) {

        Map<String, Double> dataMap;
        Type stockDataType = new TypeToken<Map<String, Double>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME_PREFIX + name + FILE_NAME_SUFFIX))) {
            dataMap = gson.fromJson(reader, stockDataType);
        } catch (IOException e) {
            throw new IllegalStateException("读取股票数据文件出错");
        }
        if (CollectionUtils.isEmpty(dataMap)) {
            String requestResult = requestForStockPrice(name);
            if (isInvalidTickerName(name, requestResult)) {
                throw new IllegalArgumentException("Invalid ticker.");
            }
            dataMap = this.storeRequestResultToMap(requestResult);
            String json = gson.toJson(dataMap);
            // 写入文件
            try (Writer writer =
                         new BufferedWriter(
                                 new OutputStreamWriter(new FileOutputStream(FILE_NAME_PREFIX + name + FILE_NAME_SUFFIX), StandardCharsets.UTF_8))) {
                writer.write(json);
            } catch (Exception e) {
                throw new IllegalStateException("写股票数据文件出错");
            }

        }
        return dataMap;
    }

    @Override
    public Double getPriceAtDate(String date, String name) {
        Map<String, Double> dataMap = queryAllStockPricesByName(name);
        if (CollectionUtils.isEmpty(dataMap)) {
            return null;
        }
        if (!dataMap.containsKey(date)) {
            throw new IllegalArgumentException("No available stock data for this day.");
        }
        return dataMap.getOrDefault(date, null);
    }

    private String requestForStockPrice(String ticker) {
        String apiKey = "CKK9C5J3C9OQ7GVT";
        String stockSymbol = ticker;
        URL url = null;

        try {
            url = new URL("https://www.alphavantage"
                    + ".co/query?function=TIME_SERIES_DAILY"
                    + "&outputsize=full"
                    + "&symbol"
                    + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
        } catch (MalformedURLException e) {
            throw new RuntimeException("the Alphavantage API has either changed or "
                    + "no longer works");
        }

        InputStream in = null;
        StringBuilder output = new StringBuilder();

        try {
            in = url.openStream();
            int b;

            while ((b = in.read()) != -1) {
                output.append((char) b);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("No price data found for " + stockSymbol);
        }

        return output.toString();
    }

    /**
     * Verify whether or not the given ticker is invalid. Return true if this ticker is invalid,
     * otherwise false.
     *
     * @param ticker Unique ticker for the stock
     * @return Return true if this ticker is invalid, otherwise false.
     */
    private boolean isInvalidTickerName(String ticker, String requestResult) {
        // net off
        String noPrice = "No price data found for " + ticker;
        if (requestResult.equals(noPrice)) {
            throw new IllegalArgumentException("Please check your network to make sure it is connected.");
        }

        // invalid ticker
        String invalidSticker = "{\n"
                + "    \"Error Message\": \"Invalid API call. Please retry or visit the documentation"
                + " (https://www.alphavantage.co/documentation/) for TIME_SERIES_DAILY.\"\n"
                + "}";
        return requestResult.equals(invalidSticker);
    }

    // store web request result to a map, key--date, value--close price
    private Map<String, Double> storeRequestResultToMap(String requestResult) {
        Map<String, Double> dataMap = Maps.newHashMap();
        Scanner scan = new Scanner(requestResult);
        // 忽略第一行
        scan.nextLine();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            String[] items = line.split(",");
            String date = items[0];
            double closePrice = Double.parseDouble(items[4]);
            dataMap.put(date, closePrice);
        }
        return dataMap;
    }
}
