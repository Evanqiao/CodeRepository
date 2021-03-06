package command;

import lombok.AllArgsConstructor;
import view.ConsoleCommand;
import view.IView;

/**
 * @author qiaoyihan
 * @date 2018-12-19
 */
@AllArgsConstructor
public class Create implements ConsoleCommand {

    private String name;

    @Override
    public void go(IView view) {
        System.out.println(view.createPortfolio(name));
    }
}
