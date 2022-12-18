package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.EmployeeDTO;
import pl.pwr.movierental.model.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);
    Employee employeeDTOToEmploye(EmployeeDTO employeeDTO);
    List<EmployeeDTO> employeeListToEmployeeDTOList(List<Employee> employeeList);
    List<Employee> employeeDTOListToEmployeeList(List<EmployeeDTO> employeeDTOList);
}
