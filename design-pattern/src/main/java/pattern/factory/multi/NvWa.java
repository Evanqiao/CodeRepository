package pattern.factory.multi;

import pattern.factory.common.Human;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class NvWa {
    public static void main(String[] args) {
        Human human = new WhiteHumanFactory().createHuman();
        human.getColor();
        human.talk();
        human = new BlackHumanFactory().createHuman();
        human.getColor();
        human.talk();
        human = new YellowHumanFactory().createHuman();
        human.getColor();
        human.talk();
    }
}
