package spring.aop.concert;

/**
 * @author qiaoyihan
 * @date 2020/5/10
 */
public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
        System.out.println("encore performance");
    }
}
