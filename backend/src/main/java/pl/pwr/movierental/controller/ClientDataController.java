package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.ClientDataDTO;
import pl.pwr.movierental.service.ClientDataService;

import java.util.List;

@RestController
@RequestMapping("/api/clients/")
public class ClientDataController {
    @Autowired
    private ClientDataService clientDataService;

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of clients", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/all")
    public ResponseEntity<List<ClientDataDTO>> getAllClientsData() {
        return clientDataService.getAll();
    }

    @Operation(summary = "Get client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClientData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientDataById(@PathVariable Integer id) {
        return clientDataService.getById(id);
    }

    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ClientData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "ClientData not given", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> createClientData(@RequestBody ClientDataDTO clientData) {
        return clientDataService.add(clientData);
    }

    @Operation(summary = "Update client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ClientData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "ClientData not given", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClientData(@PathVariable Integer id, @RequestBody ClientDataDTO newClientData) {
        return clientDataService.change(id, newClientData);
    }

    @Operation(summary = "Delete address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Integer id) {
        return clientDataService.delete(id);
    }
}
