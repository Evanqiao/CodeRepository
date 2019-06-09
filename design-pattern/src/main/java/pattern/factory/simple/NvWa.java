package pattern.factory.simple;

import pattern.factory.common.BlackHuman;
import pattern.factory.common.Human;
import pattern.factory.common.WhiteHuman;
import pattern.factory.common.YellowHuman;

/**
 * 静态工厂方法模式 或 简单工厂方法模式
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class NvWa {
    public static void main(String[] args) {
        Human human = HumanFactory.createHuman(WhiteHuman.class);
        human.getColor();
        human.talk();
        human = HumanFactory.createHuman(BlackHuman.class);
        human.getColor();
        human.talk();
        human = HumanFactory.createHuman(YellowHuman.class);
        human.getColor();
        human.talk();
    }
}
