package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.ClientDataDTO;

import java.util.List;

public interface ClientDataService {
    ResponseEntity<List<ClientDataDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(ClientDataDTO clientData);
    ResponseEntity<?> change(Integer id, ClientDataDTO newClientData);
    ResponseEntity<String> delete(Integer id);
}
