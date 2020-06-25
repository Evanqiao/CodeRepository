package guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaoyihan
 * @date 2020/6/19
 */
public class CacheDemo {
    public static void main(String[] args) throws ExecutionException {
        MyTicker ticker = new MyTicker();

        Cache<Long, FibonacciSupplier> cache =
                CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .ticker(ticker)
                        .recordStats()
                        .build();

        FibonacciSupplier supplier = new FibonacciSupplier(5L);
        supplier.process();
        cache.put(5L, supplier);

        FibonacciSupplier f1 = cache.get(7L, () -> getIfAbsent(7L));

        FibonacciSupplier f = cache.getIfPresent(7L);
        System.out.println(f1);

        CacheStats cacheStats = cache.stats();

        System.out.println(cacheStats);
    }

    private static FibonacciSupplier getIfAbsent(Long key) {
        System.out.println("cache does not hit，key：");
        return new FibonacciSupplier(key).process();
    }
}
