import dal.api.QueryStockDataService;
import dal.impl.QueryStockDataServiceWithWebApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * QueryStockDataServiceWithWebApi Tester.
 *
 * @author <Authors name>
 * @since <pre>Dec 6, 2018</pre>
 * @version 1.0
 */
public class QueryStockDataServiceWithWebApiTest {

    private QueryStockDataService queryStockDataService;

    @Before
    public void before() throws Exception {
        queryStockDataService = new QueryStockDataServiceWithWebApi();
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: queryAllStockPricesByName(String name)
     *
     */
    @Test
    public void testQueryAllStockPricesByName() throws Exception {

        Map<String, Double>  dataMap = queryStockDataService.queryAllStockPricesByName("GOOG");
        System.out.println(dataMap);
    }

    /**
     *
     * Method: getPriceAtDate(LocalDate date, String name)
     *
     */
    @Test
    public void testGetPriceAtDate() throws Exception {
        LocalDate date = LocalDate.of(2018, 1, 30);
        Double price = queryStockDataService.getPriceAtDate(LocalDate.of(2018, 1, 31).toString(), "GOOG");
        assertEquals(1169.94, price, 0.01);
    }

    @Test
    public void testDate() {
        LocalDate date = LocalDate.of(2018, 1, 30);
        LocalDate date2 = LocalDate.parse("2018-01-30");
        assertEquals(date, date2);
    }

} 
