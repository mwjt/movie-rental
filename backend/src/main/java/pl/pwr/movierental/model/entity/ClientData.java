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
@Table(name = "client_data", schema = "public")
public class ClientData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_data_generator")
    @SequenceGenerator(name = "client_data_generator", sequenceName = "client_data_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "account_balance", length = 19, nullable = false)
    private BigDecimal accountBalance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;
}
