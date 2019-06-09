package pattern.factory.common;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黑色人种。");
    }

    @Override
    public void talk() {
        System.out.println("黑色人种在说话...");
    }
}
