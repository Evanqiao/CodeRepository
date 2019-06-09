package pattern.factory.multi;

import pattern.factory.common.BlackHuman;
import pattern.factory.common.Human;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class BlackHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
