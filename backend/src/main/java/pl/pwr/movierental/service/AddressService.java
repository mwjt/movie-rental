package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    ResponseEntity<List<AddressDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(AddressDTO address);
    ResponseEntity<?> change(Integer id, AddressDTO newAddress);
    ResponseEntity<String> delete(Integer id);
}
