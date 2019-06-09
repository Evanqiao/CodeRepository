package pattern.factory.multi;

import pattern.factory.common.Human;
import pattern.factory.common.WhiteHuman;

/**
 * @author qiaoyihan
 * @date 2019-06-09
 */
public class WhiteHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new WhiteHuman();
    }
}
