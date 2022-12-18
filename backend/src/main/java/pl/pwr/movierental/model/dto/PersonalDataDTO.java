package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDataDTO {
    private int id;
    private String name;
    private String surname;
    private BigInteger phoneNumber;
    private UserDTO userDTO;
}
