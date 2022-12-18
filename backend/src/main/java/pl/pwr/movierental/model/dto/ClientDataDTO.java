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
public class ClientDataDTO {
    private int id;
    private BigDecimal accountBalance;
    private AddressDTO address;
    private PersonalDataDTO personalData;
}
