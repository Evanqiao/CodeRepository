package model.impl;

import com.google.common.collect.Lists;
import dal.api.InvestDAO;
import dal.api.QueryStockDataService;
import dal.api.TransactionDAO;
import dal.impl.InvestDAOImpl;
import dal.impl.QueryStockDataServiceWithWebApi;
import dal.impl.TransactionDAOImpl;
import model.api.dto.PortfolioDTO;
import model.api.IModel;
import model.api.dto.StockDTO;
import model.api.dto.TransactionDTO;
import model.api.enums.TransactionType;
import org.springframework.util.CollectionUtils;
import utils.InvestUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public class Model implements IModel {

    private InvestDAO investDAO;
    private InvestUtil investUtil;
    private QueryStockDataService queryStockDataService;
    private TransactionDAO transactionDAO;

    public Model() {
        investDAO = new InvestDAOImpl();
        investUtil = new InvestUtil();
        queryStockDataService = new QueryStockDataServiceWithWebApi();
        transactionDAO = new TransactionDAOImpl();
    }

    @Override
    public void buyStock(
            String ticker,
            LocalDateTime timeStamp,
            double volume,
            String portfolioName,
            double commissionFee) {

        if (Objects.isNull(ticker) || Objects.isNull(timeStamp) || Objects.isNull(portfolioName)) {
            throw new IllegalArgumentException("Invalid null argument.");
        }
        if (ticker.length() == 0 || volume == 0) {
            throw new IllegalArgumentException("Argument can not be empty.");
        }
        if (!investUtil.validTimeForTransaction(timeStamp)) {
            throw new IllegalArgumentException("The stock market is closed.");
        }
        // 更新账户中的股票数量
        PortfolioDTO portfolioDTO = this.getPortfolioByName(portfolioName);
        if (Objects.isNull(portfolioDTO)) {
            throw new IllegalArgumentException("There is no such portfolio.");
        }
        List<StockDTO> stockDTOS = portfolioDTO.getStockDTOS();
        if (Objects.isNull(stockDTOS)) {
            stockDTOS = Lists.newArrayList(StockDTO.builder().name(ticker).totalVolume(volume).build());
            portfolioDTO.setStockDTOS(stockDTOS);
        } else {
            StockDTO originStock = stockDTOS.stream()
                    .filter(Objects::nonNull)
                    .filter(s -> Objects.equals(ticker, s.getName()))
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(originStock) || Objects.isNull(originStock.getTotalVolume())) {
                stockDTOS.add(StockDTO.builder().name(ticker).totalVolume(volume).build());
            } else {
                originStock.setTotalVolume(originStock.getTotalVolume() + volume);
            }
        }
        // 更新交易信息
        TransactionDTO transactionDTO = TransactionDTO.builder().type(TransactionType.BUY)
                .portfolioName(portfolioName)
                .stockName(ticker)
                .transactionDate(timeStamp.toLocalDate().toString())
                .price(queryStockDataService.getPriceAtDate(timeStamp.toLocalDate().toString(), ticker))
                .commissionFee(commissionFee)
                .volume(volume)
                .build();
        // 写会文件
        transactionDAO.addTransaction(transactionDTO);
        // 写回文件
        investDAO.updatePortfolio(portfolioDTO);

    }

    @Override
    public void createPortfolio(String name) {
        if (Objects.isNull(name) || name.length() == 0) {
            throw new IllegalArgumentException("The name is invalid.");
        }
        // 从文件里检索当前账户
        if (Objects.nonNull(investDAO.getPortfolioByName(name))) {
            throw new IllegalArgumentException("The portfolio already exist.");
        }
        PortfolioDTO portfolioDTO = PortfolioDTO.builder().name(name).build();
        // 把创建的Portfolio存入文件
        investDAO.addPortfolio(portfolioDTO);
    }

    @Override
    public List<PortfolioDTO> getPortfolioList() {
        return investDAO.getAllPortfolios();
    }

    @Override
    public PortfolioDTO examPortfolioState(String name) {
        return investDAO.getPortfolioByName(name);
    }

    @Override
    public Double determineCostBasisOfPortfolio(String name, LocalDate date) {
        List<TransactionDTO> transactionDTOS = this.getAllTransactions(name);
        if (CollectionUtils.isEmpty(transactionDTOS)) {
            return 0.0;
        }
        return transactionDTOS.stream()
                .filter(Objects::nonNull)
                .filter(t -> investUtil.isBeforeDate(t.getTransactionDate(), date))
                .mapToDouble(t -> {
                    if (Objects.equals(t.getType(), TransactionType.BUY)) {
                        return 0.0 - t.getPrice() * t.getVolume() + t.getCommissionFee();
                    } else {
                        return t.getPrice() * t.getVolume() + t.getCommissionFee();
                    }
                })
                .sum();
    }

    @Override
    public Double determineTotalValuePortfolio(String name, LocalDate date) {
        PortfolioDTO portfolioDTO = this.getPortfolioByName(name);
        if (Objects.isNull(portfolioDTO)) {
            throw new IllegalStateException("No such portfolio");
        }
        List<StockDTO> stockDTOS = portfolioDTO.getStockDTOS();
        if (CollectionUtils.isEmpty(stockDTOS)) {
            return 0.0;
        }
        return stockDTOS.stream()
                .filter(Objects::nonNull)
                .mapToDouble(s -> {
                    Double price = queryStockDataService.getPriceAtDate(date.toString(), s.getName());
                    if (Objects.isNull(price) || Objects.isNull(s.getTotalVolume())) {
                        return 0.0;
                    }
                    return price * s.getTotalVolume();
                })
                .sum();
    }

    @Override
    public List<TransactionDTO> getAllTransactions(String name) {
        List<TransactionDTO> transactionDTOS = transactionDAO.queryAllTransaction();
        if (CollectionUtils.isEmpty(transactionDTOS)) {
            return Collections.emptyList();
        }
        return transactionDTOS.stream()
                .filter(Objects::nonNull)
                .filter(t -> Objects.equals(t.getPortfolioName(), name))
                .collect(Collectors.toList());
    }

    @Override
    public void oneTimeInvest(
            String name, double money, LocalDate date, List<Double> fee, List<Double> ratio) {}

    @Override
    public void dollarCostAvgInvestment(
            String name,
            double money,
            LocalDate startDate,
            LocalDate endDate,
            int gapBetweenInvest,
            List<Double> fee,
            List<Double> ratio) {}

    private PortfolioDTO getPortfolioByName(String name) {
        List<PortfolioDTO> portfolioDTOS = getPortfolioList();
        if (CollectionUtils.isEmpty(portfolioDTOS)) {
            return null;
        }
        return portfolioDTOS
                .stream()
                .filter(Objects::nonNull)
                .filter(p -> Objects.equals(name, p.getName()))
                .findFirst()
                .orElse(null);
    }
}
