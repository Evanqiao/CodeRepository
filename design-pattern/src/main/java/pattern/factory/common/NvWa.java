package pattern.factory.common;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class NvWa {
    public static void main(String[] args) {
        AbstractHumanFactory humanFactory = new HumanFactory();
        Human human = humanFactory.createHuman(WhiteHuman.class);
        human.getColor();
        human.talk();
        human = humanFactory.createHuman(BlackHuman.class);
        human.getColor();
        human.talk();
        human = humanFactory.createHuman(YellowHuman.class);
        human.getColor();
        human.talk();
    }
}
