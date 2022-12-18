package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.PersonalDataDTO;

import java.util.List;

public interface PersonalDataService {
    ResponseEntity<List<PersonalDataDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(PersonalDataDTO personalData);
    ResponseEntity<?> change(Integer id, PersonalDataDTO newPersonalData);
    ResponseEntity<String> delete(Integer id);
}
