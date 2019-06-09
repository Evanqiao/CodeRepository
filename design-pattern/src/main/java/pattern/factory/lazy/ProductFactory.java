package pattern.factory.lazy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 * 延迟加载框架是可以扩展的，例如限制某一个产品类的最大实例化数量，可以通过判断Map中已有的对象数量来实现，
 * 这样的处理是非常有意义的，例如JDBC连接数据库，都会要求设置一个MaxConnections最大连接数量，
 * 该数量就是内存中最大实例化的数量。
 * 延迟加载还可以用在对象初始化比较复杂的情况下，例如硬件访问，涉及多方面的交互，则可以通过延迟加载降低
 * 对象的产生和销毁带来的复杂性。
 */
public class ProductFactory {
    private static final Map<String, IProduct> preMap = new HashMap<>();

    public static synchronized IProduct createProduct(String name) {
        IProduct product = null;
        if (preMap.containsKey(name)) {
            product = preMap.get(name);
        } else {
            if (Objects.equals(name, "product1")) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
        }
        return product;
    }

}
