package pattern.factory.common;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class HumanFactory extends AbstractHumanFactory {
    @Override
    public <T extends Human> T createHuman(Class<T> tClass) {
        Human human = null;
        try {
            human = (T)Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("创建失败。");
        }
        return (T)human;
    }
}
