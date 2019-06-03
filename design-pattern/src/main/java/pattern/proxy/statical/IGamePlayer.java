package pattern.proxy.statical;

/**
 * @author qiaoyihan
 * @date 2019-06-03
 */
public interface IGamePlayer {
    /**
     * 登录
     * @param user 用户名
     * @param pwd 密码
     */
    void login(String user, String pwd);

    /**
     * 杀怪
     */
    void killBoss();

    /**
     * 升级
     */
    void upgrade();
}
