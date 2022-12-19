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
@Table(name = "role", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ERole name;
}
