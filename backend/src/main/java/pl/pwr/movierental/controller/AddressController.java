package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.AddressDTO;
import pl.pwr.movierental.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/addresses/")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "Get all addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of addresses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/all")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return addressService.getAll();
    }

    @Operation(summary = "Get address by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Integer id) {
        return addressService.getById(id);
    }

    @Operation(summary = "Add a new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Address not given", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO address) {
        return addressService.add(address);
    }

    @Operation(summary = "Update address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Address not given", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Integer id, @RequestBody AddressDTO newAddress) {
        return addressService.change(id, newAddress);
    }

    @Operation(summary = "Delete address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
        return addressService.delete(id);
    }
}
