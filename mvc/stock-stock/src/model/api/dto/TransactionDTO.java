package model.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.api.enums.TransactionType;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private String portfolioName;
    private String stockName;
    /** transaction type */
    private TransactionType type;
    /** transaction date */
    private String transactionDate;
    /** transaction price */
    private Double price;
    /** transaction amount */
    private Double volume;
    /** transaction commission fee */
    private Double commissionFee;
}
