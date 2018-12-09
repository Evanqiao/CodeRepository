package dal.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dal.api.InvestDAO;
import model.api.dto.PortfolioDTO;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
public class InvestDAOImpl implements InvestDAO {

    private Gson gson;
    private static final String INVEST_JSON = "stock-stock/res/invest_json.txt";

    public InvestDAOImpl() {
        gson = new GsonBuilder().create();
    }

    @Override
    public PortfolioDTO getPortfolioByName(String name) {
        List<PortfolioDTO> portfolioDTOS = this.getAllPortfolios();
        if (CollectionUtils.isEmpty(portfolioDTOS)) {
            return null;
        }
        return portfolioDTOS
                .stream()
                .filter(Objects::nonNull)
                .filter(p -> Objects.equals(name, p.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addPortfolio(PortfolioDTO portfolio) {

        List<PortfolioDTO> originPortfolio = this.getAllPortfolios();
        List<PortfolioDTO> portfolioDTOS = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(originPortfolio)) {
            portfolioDTOS.addAll(originPortfolio);
        }
        portfolioDTOS.add(portfolio);
        this.updatePortfolios(portfolioDTOS);
    }

    @Override
    public List<PortfolioDTO> getAllPortfolios() {
        Type portfoliosType = new TypeToken<List<PortfolioDTO>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(Paths.get(INVEST_JSON))) {
            return gson.fromJson(reader, portfoliosType);
        } catch (IOException e) {
            throw new IllegalStateException("读取文件出错");
        }
    }

    @Override
    public void updatePortfolio(PortfolioDTO portfolio) {
        if (Objects.isNull(portfolio) || Objects.isNull(portfolio.getName())) {
            throw new IllegalArgumentException("have no args");
        }

        List<PortfolioDTO> portfolioDTOS = this.getAllPortfolios();
        if (CollectionUtils.isEmpty(portfolioDTOS)) {
            return;
        }
        PortfolioDTO originPortfolioDTO = portfolioDTOS
                .stream()
                .filter(Objects::nonNull)
                .filter(p -> Objects.equals(portfolio.getName(), p.getName()))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(originPortfolioDTO) || Objects.isNull(originPortfolioDTO.getName())) {
            throw new IllegalStateException("have no origin Portfolio");
        }
        originPortfolioDTO.setStockDTOS(portfolio.getStockDTOS());
        this.updatePortfolios(portfolioDTOS);
    }

    private void updatePortfolios(List<PortfolioDTO> portfolioDTOS) {
        if (CollectionUtils.isEmpty(portfolioDTOS)) {
            return;
        }
        String json = gson.toJson(portfolioDTOS);
        try (Writer writer =
                     new BufferedWriter(
                             new OutputStreamWriter(new FileOutputStream(INVEST_JSON), StandardCharsets.UTF_8))) {
            writer.write(json);
        } catch (Exception e) {
            throw new IllegalStateException("写文件出错");
        }
    }
}
