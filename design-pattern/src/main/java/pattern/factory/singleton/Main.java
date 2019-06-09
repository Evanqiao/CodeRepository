package pattern.factory.singleton;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class Main {
    public static void main(String[] args) {
        Singleton s1 = SingletonFactory.getSingleton();
        Singleton s2 = SingletonFactory.getSingleton();
        System.out.println(s1 == s2);
    }
}
