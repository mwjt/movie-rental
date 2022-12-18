package pl.pwr.movierental.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String status;

    private Date rentalDate;

    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "clientid", referencedColumnName = "clientid")
    private ClientData clientData;

    @ManyToOne
    @JoinColumn(name = "filmid", referencedColumnName = "filmid")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "employeeid", referencedColumnName = "employeeid")
    private Employee employee;
}
