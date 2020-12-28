package guava;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import common.ServiceException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaoyihan
 * @date 2020/4/14
 */
public class RetryTest {
    public static void main(String[] args) {
        try {
            work();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("### " + e.getMessage());
            System.out.println("+++++ " + e.toString());
        }
    }

    private static void work() throws Exception {
        Retryer<Integer> retry =
                RetryerBuilder.<Integer>newBuilder()
                        .retryIfExceptionOfType(ServiceException.class)
                        .retryIfResult(v -> Objects.equals(v, -1))
                        .withWaitStrategy(
                                WaitStrategies.randomWait(
                                        500, TimeUnit.MILLISECONDS, 1000, TimeUnit.MILLISECONDS))
                        .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                        .build();

        retry.call(
                () -> {
                    System.out.println("************");
                    return -1;
                });
    }
}
