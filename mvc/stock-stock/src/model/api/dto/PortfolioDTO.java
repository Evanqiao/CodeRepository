package model.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PortfolioDTO {
    /** portfolio name */
    private String name;
    /** ticker */
    private List<StockDTO> stockDTOS;
}
