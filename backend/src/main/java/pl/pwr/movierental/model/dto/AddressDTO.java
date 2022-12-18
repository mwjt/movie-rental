package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private int addressId;
    private String street;
    private String buildingNumber;
    private String flatNumber;
    private String postalCode;
    private CityDTO city;
}
