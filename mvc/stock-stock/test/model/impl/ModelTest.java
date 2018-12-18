import model.api.IModel;
import model.api.dto.PortfolioDTO;
import model.api.dto.TransactionDTO;
import model.impl.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model Tester.
 *
 * @author <Authors name>
 * @since <pre>Dec 6, 2018</pre>
 * @version 1.0
 */
public class ModelTest {

    IModel model;

    @Before
    public void before() throws Exception {
        model = new Model();
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: buyStock(String ticker, LocalDateTime timeStamp, double volume, String portfolio, double commissionFee)
     *
     */
    @Test
    public void testBuyStock() throws Exception {

        model.buyStock("GOOG", LocalDateTime.of(2018, 11, 7, 11, 3),
            10, "QQQ", 0);
        model.buyStock("GOOG", LocalDateTime.of(2018, 11, 7, 11, 3),
                10, "QQQ", 0);

    }

    /**
     *
     * Method: createPortfolio(String name)
     *
     */
    @Test
    public void testCreatePortfolio() throws Exception {
        model.createPortfolio("QQQ");
    }

    /**
     *
     * Method: getPortfolioList()
     *
     */
    @Test
    public void testGetPortfolioList() throws Exception {
        List<PortfolioDTO> portfolioDTOList = model.getPortfolioList();
        System.out.println(portfolioDTOList.toString());
    }

    /**
     *
     * Method: examPortfolioState(String name)
     *
     */
    @Test
    public void testExamPortfolioState() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: determinePortfolio(String name, LocalDate date)
     *
     */
    @Test
    public void testDeterminePortfolio() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: addStockToPortfolio(String name, String ticker)
     *
     */
    @Test
    public void testAddStockToPortfolio() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: getAllTransactions(String name)
     *
     */
    @Test
    public void testGetAllTransactions() throws Exception {
        List<TransactionDTO> transactionDTOS = model.getAllTransactions("QQQ");
        System.out.println(transactionDTOS);
    }

    /**
     *
     * Method: noSuchPortfolio(String name)
     *
     */
    @Test
    public void testNoSuchPortfolio() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: oneTimeInvest(String name, double money, LocalDate date, List<Double> fee, List<Double> ratio)
     *
     */
    @Test
    public void testOneTimeInvest() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: dollarCostAvgInvestment(String name, double money, LocalDate startDate, LocalDate endDate, int gapBetweenInvest, List<Double> fee, List<Double> ratio)
     *
     */
    @Test
    public void testDollarCostAvgInvestment() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: getPortfolioSize(String name)
     *
     */
    @Test
    public void testGetPortfolioSize() throws Exception {
//TODO: Test goes here... 
    }


} 
