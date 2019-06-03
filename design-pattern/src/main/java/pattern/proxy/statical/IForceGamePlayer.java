package pattern.proxy.statical;

/**
 * @author qiaoyihan
 * @date 2019-06-03
 */
public interface IForceGamePlayer extends IGamePlayer {
    /**
     * 获取自己的代理
     * @return 玩家对象
     */
    IGamePlayer getProxy();
}
