package pl.pwr.movierental.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_data", schema = "public")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_data_generator")
    @SequenceGenerator(name = "personal_data_generator", sequenceName = "personal_data_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "surname", length = 50, nullable = false)
    private String surname;
    @Column(name = "phone_number", length = 9, nullable = false)
    private BigInteger phoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
