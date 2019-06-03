package pattern.proxy.statical;

/**
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class Client {
    public static void main(String[] args) {
        // 在该模式下，调用者只知代理而不用知道真实的角色是谁，
        // 屏蔽了真实角色的变更对高层模块的影响，
        // 真实的主题角色想怎么修改就怎么修改，对高层次的模块没有
        // 任何的影响，只要你实现了接口所对应的方法，该模式非常适合对扩展性要求较高的场合。
        IGamePlayer proxy = new CommonProxyPlayer("张三");

        proxy.login("zhangSan", "pwd");
        proxy.killBoss();
        proxy.upgrade();

        System.out.println("*************");

        // 强制代理的概念就是要从真实角色查找到代理角色
        IForceGamePlayer player = new ForcePlayer("李四");
        IGamePlayer proxy2 = player.getProxy();
        proxy2.login("lisi", "pwd");
        proxy2.killBoss();
        proxy2.upgrade();
    }
}
