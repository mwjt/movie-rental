package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDataDTO {
    private int clientId;
    private double accountBalance;
    private AddressDTO address;
    private PersonalDataDTO personalData;
}
