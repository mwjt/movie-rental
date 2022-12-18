package pl.pwr.movierental.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PersonalData")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personalDataId;

    private String name;

    private String surname;

    private Integer phoneNumber;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;
}
