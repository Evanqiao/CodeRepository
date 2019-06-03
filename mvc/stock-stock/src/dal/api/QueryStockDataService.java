package dal.api;

import java.util.Map;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public interface QueryStockDataService {

    Map<String, Double> queryAllStockPricesByName(String name);

    Double getPriceAtDate(String date, String name);
}
