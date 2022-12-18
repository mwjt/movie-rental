package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int id;
    private String status;
    private Date rentalDate;
    private Date returnDate;
    private ClientDataDTO clientData;
    private FilmDTO film;
    private EmployeeDTO employee;
}
