package pattern.factory.multi;

import pattern.factory.common.Human;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public abstract class AbstractHumanFactory {
    /**
     * 创建人类
     * @return 创建出的人类对象
     */
    public abstract Human createHuman();
}
