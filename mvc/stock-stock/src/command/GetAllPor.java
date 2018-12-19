package command;

import view.ConsoleCommand;
import view.IView;

/**
 * @author qiaoyihan
 * @date 2018-12-19
 */
public class GetAllPor implements ConsoleCommand {

    @Override
    public void go(IView view) {
        System.out.println(view.getPortfolioList());
    }
}
