package view;

/**
 * @author qiaoyihan
 * @date 2018-12-18
 */
public interface IView {

    /**
     * 购买指定名字，指定日期，一定量的股票到指定账户中
     * @param ticker  股票名字
     * @param date  日期 YYYY-MM-DD
     * @param volume 购买股票的数量
     * @param portfolioName 要放入的理财包名称
     * @param commissionfee 手续费
     * @return 购买成功的提示信息
     */
    String buyStock(String ticker,
                  String date,
                  double volume,
                  String portfolioName,
                  double commissionfee);

    /**
     * 创建理财包
     * @param name 名称
     * @return 创建成功提示信息
     */
    String createPortfolio(String name);

    /**
      * 获取所有的理财包信息
      * @return 理财包信息
     */
    String getPortfolioList();

    /**
     * 获取指定名字的理财包信息
     * @param name 理财包名称
     * @return 展示信息
     */
    String examPortfolioState(String name);

    /**
     * 获取指定的理财包在传入的日期前花费的总额
     * @param name 理财包名称
     * @param date 日期
     * @return 展示信息
     */
    String determineCostBasisOfPortfolio(String name, String date);

    /**
     * 获取当前日期下指定理财包里面股票的总价值
     * @param name 理财包名称
     * @param date 日期
     * @return 展示信息
     */
    String determineTotalValuePortfolio(String name, String date);

    /**
     * 获取指定理财包的所有交易信息
     * @param name 理财包名称
     * @return 展示信息
     */
    String getAllTransactions(String name);
}
