package concurrent.threadlocal;

import java.lang.ref.WeakReference;

/**
 * @author qiaoyihan
 * @date 2020/6/13
 */
public class WeakReferenceWhenIdle {
    public static void main(String[] args) {
        House seller = new House();
        WeakReference<House> buyer2 = new WeakReference<>(seller);
        seller = null;
        long start = System.nanoTime();
        for (long i = 0; ; i++) {
            if (buyer2.get() == null) {
                long duration = (System.nanoTime() - start) / (1000 * 1000);
                System.out.println("house is null and exited time:" + duration + "ms");
                break;
            } else {
                System.out.println("still there. count = " + (i++));
            }
        }
    }
}
