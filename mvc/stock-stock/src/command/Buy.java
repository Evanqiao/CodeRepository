package command;

import lombok.AllArgsConstructor;
import view.ConsoleCommand;
import view.IView;

/**
 * @author qiaoyihan
 * @date 2018-12-18
 */
@AllArgsConstructor
public class Buy implements ConsoleCommand {

    private String ticker;
    private String date;
    private double volume;
    private String portfolioName;
    private double commissionfee;

    @Override
    public void go(IView view) {
        view.buyStock(ticker, date, volume, portfolioName, commissionfee);
    }
}
