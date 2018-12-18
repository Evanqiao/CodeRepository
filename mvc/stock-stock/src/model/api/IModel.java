package model.api;

import model.api.dto.PortfolioDTO;
import model.api.dto.TransactionDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IModel {
  /**
   * Buy the stock from the market. The method will throw IllegalArgumentException when the argument
   * is invalid.
   *
   * @param ticker        Name for stock, if there is no stock with such name, it will be considered
   *                      as invalid
   * @param timeStamp     Time for buying stock, holidays or before 9 am, after 4 pm will be
   *                      considered as invalid
   * @param volume        Quantity for buying stock, 0 and negative numbers will be considered as
   *                      invalid
   * @param portfolioName Name for dest portfolio, if there is no such portfolio in the model, it
   *                      will be considered as invalid
   * @param commissionfee Commission fee for a transaction
   */
  void buyStock(String ticker,
                LocalDateTime timeStamp,
                double volume,
                String portfolioName,
                double commissionfee);

  /**
   * Create a portfolio with the input name.
   *
   * @param name if there is already a portfolio with such name, will throw
   *             IllegalArgumentException
   */
  void createPortfolio(String name);

  /**
   * Get all the portfolios in the model.
   *
   * @return String represents the portfolio list
   */
  List<PortfolioDTO> getPortfolioList();

  /**
   * Exam the composition of portfolio with the input name, it will return the name of all stocks
   * and the quantity the portfolio owns.
   *
   * @param name Name of the portfolio
   * @return The composition of the portfolio as a string
   */
  PortfolioDTO examPortfolioState(String name);

  /**
   * Determine the cost basis and total value of a portfolio at a certain date. The cost basis is
   * how much you spend on the portfolio before and include the date.
   *
   * @param name Name for the portfolio
   * @param date Date for the inquiry
   * @return Return the result of inquiry as a string
   */
  Double determineCostBasisOfPortfolio(String name, LocalDate date);

  Double determineTotalValuePortfolio(String name, LocalDate date);

  /**
   * Get all the transactions in the specified portfolio.
   *
   * @param name The portfolio name
   * @return All the transactions in the specified portfolio
   */
  List<TransactionDTO> getAllTransactions(String name);

  /**
   * Allow user to make a one time investment. Invest a fixed amount into an existing portfolio
   * containing multiple stocks in a certain date, using equal weights or specified weights for each
   * stock in the portfolio. There will be a commission fee for each stock.
   *
   * @param name  The specified portfolio name
   * @param money Invest amount
   * @param date  Date for the investment
   * @param fee   The commission fee for the stocks in the portfolio
   * @param ratio The weight for the stocks in the portfolio
   */
  void oneTimeInvest(String name,
                     double money,
                     LocalDate date,
                     List<Double> fee,
                     List<Double> ratio);

  /**
   * Allow user to make a periodic, long-term investment. Invest a fixed amount into an existing
   * portfolio containing multiple stocks from a start date to a end date, using equal weights or
   * specified weights for each stock in the portfolio. There will be a commission fee for each
   * stock.
   *
   * @param name             The specified portfolio name
   * @param money            Invest amount for every period
   * @param startDate        Start date for the investment
   * @param endDate          End date for the investment
   * @param gapBetweenInvest The number of gap day between investment
   * @param fee              The commission fee for the stocks in the portfolio
   * @param ratio            The weight for the stocks in the portfolio
   */
  void dollarCostAvgInvestment(String name, double money, LocalDate startDate, LocalDate endDate,
                               int gapBetweenInvest, List<Double> fee, List<Double> ratio);

}
