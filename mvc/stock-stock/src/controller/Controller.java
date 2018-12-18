package controller;

import model.api.IModel;
import model.api.dto.PortfolioDTO;
import model.api.dto.TransactionDTO;
import model.impl.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qiaoyihan
 * @date 2018-12-18
 */
public class Controller implements IController {

    private IModel model;

    public Controller() {
        model = new Model();
    }

    @Override
    public void buyStock(String ticker, LocalDateTime timeStamp, double volume, String portfolioName, double commissionfee) {
        model.buyStock(ticker, timeStamp, volume, portfolioName, commissionfee);
    }

    @Override
    public void createPortfolio(String name) {
        model.createPortfolio(name);
    }

    @Override
    public List<PortfolioDTO> getPortfolioList() {
        return model.getPortfolioList();
    }

    @Override
    public PortfolioDTO examPortfolioState(String name) {
        return model.examPortfolioState(name);
    }

    @Override
    public Double determineCostBasisOfPortfolio(String name, LocalDate date) {
        return model.determineCostBasisOfPortfolio(name, date);
    }

    @Override
    public Double determineTotalValuePortfolio(String name, LocalDate date) {
        return model.determineTotalValuePortfolio(name, date);
    }

    @Override
    public List<TransactionDTO> getAllTransactions(String name) {
        return model.getAllTransactions(name);
    }
}
