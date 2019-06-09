package pattern.factory.common;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄色人种。");
    }

    @Override
    public void talk() {
        System.out.println("黄色人种在说话...");
    }
}
