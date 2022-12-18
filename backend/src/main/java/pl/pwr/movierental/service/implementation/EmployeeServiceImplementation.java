package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.EmployeeMapper;
import pl.pwr.movierental.model.dto.EmployeeDTO;
import pl.pwr.movierental.model.entity.Employee;
import pl.pwr.movierental.repository.EmployeeRepository;
import pl.pwr.movierental.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    public ResponseEntity<List<EmployeeDTO>> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return ResponseEntity.ok(employeeMapper.employeeListToEmployeeDTOList(employeeList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(employeeMapper.employeeToEmployeeDTO(employee.get()));
    }

    @Transactional
    public ResponseEntity<?> add(EmployeeDTO employee) {
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Employee newEmployee = employeeRepository.saveAndFlush(employeeMapper.employeeDTOToEmploye(employee));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.employeeToEmployeeDTO(newEmployee));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, EmployeeDTO newEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newEmployee.setId(id);
        employeeRepository.saveAndFlush(employeeMapper.employeeDTOToEmploye(newEmployee));
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(employeeRepository::delete);
        return ResponseEntity.ok("");
    }
}
