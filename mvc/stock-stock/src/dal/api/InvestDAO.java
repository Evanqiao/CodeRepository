package dal.api;

import model.api.dto.PortfolioDTO;

import java.util.List;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public interface InvestDAO {
    /**
     * 通过名字去文件里查找Portfolio
     * @param name Portfolio名字
     * @return 如果找到，返回，没找到返回null
     */
    PortfolioDTO getPortfolioByName(String name);

    /**
      * 添加Portfolio到文件中
      * @param portfolio 要保存的PortfolioDTO
     */
    void addPortfolio(PortfolioDTO portfolio);

    /**
      * 获取所有的portfolio
      * @return
     */
    List<PortfolioDTO> getAllPortfolios();

    void updatePortfolio(PortfolioDTO portfolio);
}
