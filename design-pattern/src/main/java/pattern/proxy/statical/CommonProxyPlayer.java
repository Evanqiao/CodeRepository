package pattern.proxy.statical;

/**
 * 普通代理
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class CommonProxyPlayer implements IGamePlayer {

    private IGamePlayer gamePlayer = null;


    public CommonProxyPlayer(String name) {
        // 构造函数传递对谁进行代练
        try {
            gamePlayer = new CommonPlayer(this, name);
        } catch (Exception e) {
            // 处理异常
        }
    }

    @Override
    public void login(String user, String pwd) {
        this.gamePlayer.login(user, pwd);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
    }
}
