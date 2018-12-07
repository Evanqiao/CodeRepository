package dal.api;

import model.api.dto.TransactionDTO;

import java.util.List;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public interface TransactionDAO {

    void addTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> queryAllTransaction();
}
