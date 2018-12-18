package view;

import com.google.common.base.Strings;
import controller.Controller;
import controller.IController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qiaoyihan
 * @date 2018-12-18
 */
public class ConsoleViewImpl implements IView {

    private IController controller;

    public ConsoleViewImpl() {
        controller = new Controller();
    }

    @Override
    public String buyStock(String ticker, String date, double volume, String portfolioName, double commissionfee) {
        if (Strings.isNullOrEmpty(ticker) || Strings.isNullOrEmpty(date) || Strings.isNullOrEmpty(portfolioName)) {
            return "必填参数不能为空";
        }
        controller.buyStock(ticker, LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME), volume, portfolioName, commissionfee);
        return "购买成功";
    }

    @Override
    public String createPortfolio(String name) {
        return null;
    }

    @Override
    public String getPortfolioList() {
        return null;
    }

    @Override
    public String examPortfolioState(String name) {
        return null;
    }

    @Override
    public String determineCostBasisOfPortfolio(String name, String date) {
        return null;
    }

    @Override
    public String determineTotalValuePortfolio(String name, String date) {
        return null;
    }

    @Override
    public String getAllTransactions(String name) {
        return null;
    }
}
