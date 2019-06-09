package pattern.factory.common;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("白色人种。");
    }

    @Override
    public void talk() {
        System.out.println("白色人种在说话...");
    }
}
