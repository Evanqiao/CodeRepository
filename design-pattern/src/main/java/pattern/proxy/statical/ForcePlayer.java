package pattern.proxy.statical;

/**
 * 强制代理
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class ForcePlayer implements IForceGamePlayer {
    private String name = "";
    /** 我的代理是谁 */
    private IGamePlayer proxy = null;

    public ForcePlayer(String name) {
        this.name = name;
    }
    @Override
    public IGamePlayer getProxy() {
        // 找到自己的代理
        this.proxy = new ForceProxyPlayer(this);
        return this.proxy;
    }

    @Override
    public void killBoss() {
        // 打怪，最期望的就是杀老怪
        if (this.isProxy()) {
            System.out.println(this.name + "在打怪！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    @Override
    public void login(String user, String password) {
        // 进游戏之前你肯定要登录吧，这是一个必要条件
        if (this.isProxy()) {
            System.out.println("登录名为" + user + "的用户" + this.name + "登录成功！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    @Override
    public void upgrade() {
        // 升级，升级有很多方法，花钱买是一种，做任务也是一种
        if (this.isProxy()) {
            System.out.println(this.name + " 又升了一级！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    private boolean isProxy() {
        // 校验是否是代理访问
        if (this.proxy == null) {
            return false;
        } else {
            return true;
        }
    }
}
