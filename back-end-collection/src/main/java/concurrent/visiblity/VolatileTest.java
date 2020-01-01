package concurrent.visiblity;

/**
 * @author qiaoyihan
 * @date 2019/12/30
 */
public class VolatileTest {

    private static volatile int val;

    private static void addOne() {
        for (int i = 0; i < 1000; i++) {
            val++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> addOne()).start();
        }
        if (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(val);
    }
}
