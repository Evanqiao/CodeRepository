package dal.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dal.api.TransactionDAO;
import model.api.dto.PortfolioDTO;
import model.api.dto.TransactionDTO;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public class TransactionDAOImpl implements TransactionDAO {

    private Gson gson;

    private static final String TRANSACTION_JSON = "stock-stock/res/transaction_json.txt";

    public TransactionDAOImpl() {
        gson = new GsonBuilder().create();
    }

    @Override
    public void addTransaction(TransactionDTO transactionDTO) {
        List<TransactionDTO> originTransactionDTOs = this.queryAllTransaction();
        List<TransactionDTO> transactionDTOs = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(originTransactionDTOs)) {
            transactionDTOs.addAll(originTransactionDTOs);
        }
        transactionDTOs.add(transactionDTO);

        String json = gson.toJson(transactionDTOs);
        try (Writer writer =
                     new BufferedWriter(
                             new OutputStreamWriter(new FileOutputStream(TRANSACTION_JSON), StandardCharsets.UTF_8))) {
            writer.write(json);
        } catch (Exception e) {
            throw new IllegalStateException("写交易文件出错");
        }
    }

    @Override
    public List<TransactionDTO> queryAllTransaction() {
        Type transactionType = new TypeToken<List<TransactionDTO>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(Paths.get(TRANSACTION_JSON))) {
            return gson.fromJson(reader, transactionType);
        } catch (IOException e) {
            throw new IllegalStateException("读取交易文件出错");
        }
    }
}
