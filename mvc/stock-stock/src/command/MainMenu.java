package command;

import utils.InvestUtil;
import view.ConsoleCommand;
import view.IView;

/**
 * @author qiaoyihan
 * @date 2018-12-19
 */
public class MainMenu implements ConsoleCommand {

    @Override
    public void go(IView view) {
        System.out.println(InvestUtil.getIllustrativeInfo());
    }
}
