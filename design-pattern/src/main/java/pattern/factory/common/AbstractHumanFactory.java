package pattern.factory.common;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public abstract class AbstractHumanFactory {
    /**
     * 创建人类
     * @param tClass 人种类
     * @param <T> 实现了 Human 接口的类
     * @return 创建出的人类对象
     */
    public abstract <T extends Human> T createHuman(Class<T> tClass);
}
