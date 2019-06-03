package pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class ProxyHandler implements InvocationHandler {

    Subject subject = null;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("****** before ********");
        Object result = method.invoke(subject, args);
        System.out.println("****** after ********");
        return result;
    }
}
