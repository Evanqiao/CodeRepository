package concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author qiaoyihan
 * @date 2020/6/14
 */
public class InitValueInThreadLocal {
    private static final StringBuilder INIT_VALUE = new StringBuilder("init");

    private static final ThreadLocal<StringBuilder> builder =
            ThreadLocal.withInitial(() -> INIT_VALUE);

    private static class AppendStringThread extends Thread {
        @Override
        public void run() {
            //            synchronized (builder) {
            StringBuilder inThread = builder.get();
            for (int i = 0; i < 10; i++) {
                inThread.append("-").append(i);
            }
            System.out.println(inThread.toString());
        }
        //        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new AppendStringThread().start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
