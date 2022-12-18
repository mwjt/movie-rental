package pl.pwr.movierental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDataDTO {
    private int personalDataId;
    private String name;
    private String surname;
    private String email;
    private int phoneNumber;
    private String login;
    private String password;
}
