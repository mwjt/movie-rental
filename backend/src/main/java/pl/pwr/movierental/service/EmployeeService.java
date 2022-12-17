package pl.pwr.movierental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.Employee;
import pl.pwr.movierental.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return ResponseEntity.ok(employeeList);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(employee.get());
    }

    @Transactional
    public ResponseEntity<?> add(Employee employee) {
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Employee newClientData = employeeRepository.saveAndFlush(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClientData);
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, Employee newEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newEmployee.setEmployeeId(id);
        employeeRepository.saveAndFlush(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @Transactional
    public ResponseEntity<?> delete(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(employeeRepository::delete);
        return ResponseEntity.ok("");
    }
}
