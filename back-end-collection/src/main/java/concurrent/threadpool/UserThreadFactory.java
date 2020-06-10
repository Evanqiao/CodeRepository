package concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiaoyihan
 * @date 2020/6/9
 */
public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicLong nextId = new AtomicLong(1);

    UserThreadFactory(String threadGroup) {
        namePrefix = "UserThreadFactory's " + threadGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + nextId.getAndIncrement();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Thread thread = threadFactory.newThread(r);
        thread.setName(name);
        System.out.println(thread.getName());
        return thread;
    }
}
