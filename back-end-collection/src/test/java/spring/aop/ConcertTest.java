package spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.aop.concert.Encoreable;
import spring.aop.concert.Performance;
import spring.aop.config.ConcertConfig;

import static org.junit.Assert.assertNotNull;

/**
 * @author qiaoyihan
 * @date 2020/5/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class ConcertTest {

    @Autowired private Performance performance;

    @Test
    public void testShouldNotNull() {
        assertNotNull(performance);
    }

    @Test
    public void testPerform() {
        performance.perform();
    }

    @Test
    public void testEncoreable() {
        Encoreable encoreable = (Encoreable) performance;
        encoreable.performEncore();
    }
}
