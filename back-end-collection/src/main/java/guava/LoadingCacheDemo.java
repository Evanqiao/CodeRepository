package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaoyihan
 * @date 2020/6/17
 */
public class LoadingCacheDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTicker ticker = new MyTicker();

        LoadingCache<Long, FibonacciSupplier> loadingCache =
                CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.SECONDS)
                        .ticker(ticker)
                        .recordStats()
                        .build(
                                new CacheLoader<Long, FibonacciSupplier>() {
                                    @Override
                                    public FibonacciSupplier load(Long key) {
                                        FibonacciSupplier supplier = new FibonacciSupplier(key);
                                        supplier.process();
                                        return supplier;
                                    }
                                });
        Map<Long, FibonacciSupplier> map = loadingCache.getAll(Lists.newArrayList(5L, 6L, 7L));
        map = loadingCache.getAll(Lists.newArrayList(5L, 6L, 7L));
        ticker.add(9, TimeUnit.SECONDS);
        map = loadingCache.getAll(Lists.newArrayList(5L, 6L, 7L));
        ticker.add(2, TimeUnit.SECONDS);
        map = loadingCache.getAll(Lists.newArrayList(5L, 6L, 7L));

        CacheStats cacheStats = loadingCache.stats();

        System.out.println(cacheStats);
    }
}
