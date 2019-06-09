package pattern.factory.multi;

import pattern.factory.common.Human;
import pattern.factory.common.YellowHuman;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class YellowHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
