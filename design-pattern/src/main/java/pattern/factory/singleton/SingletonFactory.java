package pattern.factory.singleton;

import java.lang.reflect.Constructor;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class SingletonFactory {
    private static Singleton singleton = null;
    static {
        try {
            Class clz = Class.forName(Singleton.class.getName());
            Constructor constructor = clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            singleton = (Singleton) constructor.newInstance();
        } catch (Exception e) {
            System.out.println("类创建失败");
        }
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
