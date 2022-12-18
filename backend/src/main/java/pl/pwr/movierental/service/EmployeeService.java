package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<List<EmployeeDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(EmployeeDTO employee);
    ResponseEntity<?> change(Integer id, EmployeeDTO newEmployee);
    ResponseEntity<String> delete(Integer id);
}
