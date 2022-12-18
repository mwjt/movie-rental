package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.EmployeeDTO;
import pl.pwr.movierental.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return employeeService.getAll();
    }

    @Operation(summary = "Get employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Employee not given", content = @Content)
    })
    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.add(employee);
    }

    @Operation(summary = "Update employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Employee not given", content = @Content)
    })
    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO newEmployee) {
        return employeeService.change(id, newEmployee);
    }

    @Operation(summary = "Delete employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        return employeeService.delete(id);
    }
}
