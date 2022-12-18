package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.CityDTO;

import java.util.List;

public interface CityService {
    ResponseEntity<List<CityDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(CityDTO city);
    ResponseEntity<?> change(Integer id, CityDTO newCity);
    ResponseEntity<String> delete(Integer id);
}
