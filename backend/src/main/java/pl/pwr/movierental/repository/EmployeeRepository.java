package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
