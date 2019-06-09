package pattern.factory.simple;

import pattern.factory.common.Human;

/**
 * 简单工厂方法模式
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class HumanFactory {
    public static <T extends Human> T createHuman(Class<T> tClass) {
        Human human = null;
        try {
            human = (T)Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("创建失败。");
        }
        return (T)human;
    }
}
