package model.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiaoyihan
 * @date 2018-12-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {
    /** ticker name */
    private String name;
    /** stock amount */
    private Double totalVolume;
}
