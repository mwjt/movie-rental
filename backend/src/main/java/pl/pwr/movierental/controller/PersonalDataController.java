package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.PersonalDataDTO;
import pl.pwr.movierental.service.PersonalDataService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PersonalDataController {
    @Autowired
    private PersonalDataService personalDataService;

    @Operation(summary = "Get all personal data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of personal data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/personals")
    public ResponseEntity<List<PersonalDataDTO>> getAllPersonalData() {
        return personalDataService.getAll();
    }

    @Operation(summary = "Get personal data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PersonalData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalDataDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @GetMapping("/personal/{id}")
    public ResponseEntity<?> getPersonalDataById(@PathVariable Integer id) {
        return personalDataService.getById(id);
    }

    @Operation(summary = "Add a new personal data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PersonalData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalDataDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "PersonalData not given", content = @Content)
    })
    @PostMapping("/personals")
    public ResponseEntity<?> createPersonalData(@RequestBody PersonalDataDTO personalData) {
        return personalDataService.add(personalData);
    }

    @Operation(summary = "Update personal data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PersonalData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalDataDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "PersonalData not given", content = @Content)
    })
    @PutMapping("/personal/{id}")
    public ResponseEntity<?> updatePersonalData(@PathVariable Integer id, @RequestBody PersonalDataDTO newPersonalData) {
        return personalDataService.change(id, newPersonalData);
    }

    @Operation(summary = "Delete address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/personal/{id}")
    public ResponseEntity<String> deletePersonalData(@PathVariable Integer id) {
        return personalDataService.delete(id);
    }
}
