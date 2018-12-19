package command;

import lombok.AllArgsConstructor;
import view.ConsoleCommand;
import view.IView;

/**
 * @author qiaoyihan
 * @date 2018-12-19
 */
@AllArgsConstructor
public class Cost implements ConsoleCommand {

    private String name;
    private String date;

    @Override
    public void go(IView view) {
        System.out.println(view.determineCostBasisOfPortfolio(name, date));
    }
}
