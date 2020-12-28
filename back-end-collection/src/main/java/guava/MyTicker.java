package guava;

import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiaoyihan
 * @date 2020/6/19
 */
public class MyTicker extends Ticker {
    private final AtomicLong nanos = new AtomicLong();

    public void add(long duration, TimeUnit timeUnit) {
        nanos.addAndGet(timeUnit.toNanos(duration));
    }

    @Override
    public long read() {
        return nanos.get();
    }
}
