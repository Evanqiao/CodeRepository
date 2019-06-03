package pattern.proxy.dynamic;

/**
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("业务逻辑执行完成");
    }
}
