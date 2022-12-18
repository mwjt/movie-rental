package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private int filmId;
    private String name;
    private String description;
    private double price;
    private double pricePerMonth;
    private int amount;
}
