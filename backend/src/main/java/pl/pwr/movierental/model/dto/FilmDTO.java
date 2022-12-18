package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal pricePerMonth;
    private int amount;
}
