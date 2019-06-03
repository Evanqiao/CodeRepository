package pattern.proxy.statical;

/**
 * 强制代理
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class ForceProxyPlayer implements IForceGamePlayer {
    private IGamePlayer gamePlayer = null;

    public ForceProxyPlayer(IGamePlayer gamePlayer) {
        // 构造函数传递用户名
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void killBoss() {
        // 代练杀怪
        this.gamePlayer.killBoss();
    }

    @Override
    public void login(String user, String password) {
        // 代练登录
        this.gamePlayer.login(user, password);
    }

    @Override
    public void upgrade() {
        // 代练升级
        this.gamePlayer.upgrade();
    }

    @Override
    public IGamePlayer getProxy() {
        // 代理的代理暂时还没有，就是自己
        return this;
    }
}
