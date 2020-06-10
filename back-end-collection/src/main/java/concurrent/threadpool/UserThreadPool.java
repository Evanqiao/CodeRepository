package concurrent.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiaoyihan
 * @date 2020/6/9
 */
public class UserThreadPool {
    public static void main(String[] args) {
        UserThreadFactory f1 = new UserThreadFactory("第1机房");
        UserThreadFactory f2 = new UserThreadFactory("第2机房");

        UserRejectHandler handler = new UserRejectHandler();

        ThreadPoolExecutor threadPoolFirst =
                new ThreadPoolExecutor(
                        1, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), f1, handler);

        ThreadPoolExecutor threadPoolSecond =
                new ThreadPoolExecutor(
                        1, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), f2, handler);
        Task task = new Task();
        for (int i = 0; i < 100; i++) {
            threadPoolFirst.execute(task);
            threadPoolSecond.execute(task);
        }
    }
}

class Task implements Runnable {
    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void run() {
        System.out.println("running_" + count.getAndIncrement());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
