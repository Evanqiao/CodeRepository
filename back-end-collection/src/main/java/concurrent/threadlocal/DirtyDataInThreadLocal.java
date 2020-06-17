package concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qiaoyihan
 * @date 2020/6/14
 */
public class DirtyDataInThreadLocal {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 2; i++) {
            MyThread thread = new MyThread();
            pool.execute(thread);
        }
    }

    private static class MyThread extends Thread {
        private static boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                threadLocal.set(this.getName() + ", session info.");
                threadLocal.remove();
                flag = false;
            }
            System.out.println(this.getName() + " 线程是" + threadLocal.get());
        }
    }
}
