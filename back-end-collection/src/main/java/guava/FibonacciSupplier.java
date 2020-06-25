package guava;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * @author qiaoyihan
 * @date 2020/6/18
 */
public class FibonacciSupplier {

    /** 输入 */
    private Long input;
    /** 输出 */
    private Long result;
    /** 耗时（ms） */
    private BigDecimal duration;
    /** 计算时间 */
    private LocalDateTime time;
    /** 计算方式 */
    private Function<Long, Long> function;

    private static final boolean DEBUG_FLAG = true;

    public FibonacciSupplier(Long input) {
        this.input = input;
        this.function = this::compute;
    }

    public FibonacciSupplier process() {
        result = function.apply(input);
        time = LocalDateTime.now();
        return this;
    }

    private long compute(long val) {
        long start = System.nanoTime();
        long cur = 0;

        long first = 1;
        long second = 1;
        if (val == 1) {
            cur = first;
        }
        if (val == 2) {
            cur = second;
        }
        for (int i = 3; i <= val; i++) {
            cur = first + second;
            first = second;
            second = cur;
        }
        long end = System.nanoTime();
        this.duration =
                new BigDecimal(end - start)
                        .divide(new BigDecimal(1000 * 10000), 6, BigDecimal.ROUND_HALF_UP);
        if (DEBUG_FLAG) {
            System.out.println(
                    String.format("fibonacci 计算，输入：%d， 输出：%d，本次计算耗时：%d ns", val, cur, end - start));
        }
        return cur;
    }

    public Long getInput() {
        return input;
    }

    public void setInput(Long input) {
        this.input = input;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Function<Long, Long> getFunction() {
        return function;
    }

    public void setFunction(Function<Long, Long> function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return String.format(
                "fibonacci 计算，输入：%d， 输出：%d，本次计算耗时：%s ms，计算时间：%s",
                input, result, duration.toString(), time.toString());
    }
}
