package pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class Client {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        ProxyHandler proxyHandler = new ProxyHandler(realSubject);

        Subject proxySubject =
                (Subject)
                        Proxy.newProxyInstance(
                                realSubject.getClass().getClassLoader(),
                                realSubject.getClass().getInterfaces(),
                                proxyHandler);

        proxySubject.request();
    }
}
