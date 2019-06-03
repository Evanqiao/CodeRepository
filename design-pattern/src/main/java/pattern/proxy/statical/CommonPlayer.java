package pattern.proxy.statical;

import java.util.Objects;

/**
 * 普通代理
 * @author qiaoyihan
 * @date 2019-06-03
 */
public class CommonPlayer implements IGamePlayer {

    private String name = "";

    public CommonPlayer(IGamePlayer gamePlayer, String name) throws Exception {
        // 构造函数限制谁能创建对象，并同时传递姓名
        if (Objects.isNull(gamePlayer)) {
            throw new IllegalAccessException("不能创建真实角色");
        } else {
            this.name = name;
        }
    }
    @Override
    public void login(String user, String pwd) {
        System.out.println("用户" + user + "登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "又升了一级");
    }
}
