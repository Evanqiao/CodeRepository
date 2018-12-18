package controller;

import model.api.dto.PortfolioDTO;
import model.api.dto.TransactionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qiaoyihan
 * @date 2018-12-10
 */
public interface IController {

    void buyStock(String ticker,
                  LocalDateTime timeStamp,
                  double volume,
                  String portfolioName,
                  double commissionfee);

    void createPortfolio(String name);

    List<PortfolioDTO> getPortfolioList();

    PortfolioDTO examPortfolioState(String name);

    Double determineCostBasisOfPortfolio(String name, LocalDate date);

    Double determineTotalValuePortfolio(String name, LocalDate date);

    List<TransactionDTO> getAllTransactions(String name);
}
