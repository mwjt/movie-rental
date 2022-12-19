package pl.pwr.movierental.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film", schema = "public")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_generator")
    @SequenceGenerator(name = "film_generator", sequenceName = "film_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price", length = 5, nullable = false)
    private BigDecimal price;
    @Column(name = "price_per_month", length = 5, nullable = false)
    private BigDecimal pricePerMonth;
    @Column(name = "amount", nullable = false)
    private Integer amount;
}
