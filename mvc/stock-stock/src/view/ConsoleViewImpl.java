package view;

import com.google.common.base.Strings;
import controller.Controller;
import controller.IController;
import model.api.dto.PortfolioDTO;
import model.api.dto.TransactionDTO;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
        if (Strings.isNullOrEmpty(name)) {
            return "理财包名称不能为空";
        }
        controller.createPortfolio(name);
        return "创建成功";
    }

    @Override
    public String getPortfolioList() {
        List<PortfolioDTO> portfolioDTOS = controller.getPortfolioList();
        if (CollectionUtils.isEmpty(portfolioDTOS)) {
            return "无理财包";
        }
        return portfolioDTOS.toString();
    }

    @Override
    public String examPortfolioState(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return "理财包名称不能为空";
        }
        PortfolioDTO portfolioDTO = controller.examPortfolioState(name);
        if (Objects.isNull(portfolioDTO)) {
            return "无此理财包";
        }
        return portfolioDTO.toString();
    }

    @Override
    public String determineCostBasisOfPortfolio(String name, String date) {
        if(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(date)) {
            return "必填参数不能为空";
        }
        Double cost = controller.determineCostBasisOfPortfolio(name, LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
        if (Objects.isNull(cost)) {
            return "计算失败";
        }
        return cost.toString();
    }

    @Override
    public String determineTotalValuePortfolio(String name, String date) {
        if(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(date)) {
            return "必填参数不能为空";
        }
        Double value = controller.determineTotalValuePortfolio(name, LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
        if (Objects.isNull(value)) {
            return "计算失败";
        }
        return value.toString();
    }

    @Override
    public String getAllTransactions(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return "请指定有效的理财包";
        }
        List<TransactionDTO> transactionDTO = controller.getAllTransactions(name);
        if (CollectionUtils.isEmpty(transactionDTO)) {
            return "此理财包无交易信息";
        }
        return transactionDTO.toString();
    }
}
