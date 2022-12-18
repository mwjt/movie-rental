package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.CityDTO;
import pl.pwr.movierental.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CityController {
    @Autowired
    private CityService cityService;

    @Operation(summary = "Get all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of cities", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/cities")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return cityService.getAll();
    }

    @Operation(summary = "Get city by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Integer id) {
        return cityService.getById(id);
    }

    @Operation(summary = "Add a new city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "City not given", content = @Content)
    })
    @PostMapping("/cities")
    public ResponseEntity<?> createCity(@RequestBody CityDTO city) {
        return cityService.add(city);
    }

    @Operation(summary = "Update city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "City", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "City not given", content = @Content)
    })
    @PutMapping("/city/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody CityDTO newCity) {
        return cityService.change(id, newCity);
    }

    @Operation(summary = "Delete city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Integer id) {
        return cityService.delete(id);
    }
}
