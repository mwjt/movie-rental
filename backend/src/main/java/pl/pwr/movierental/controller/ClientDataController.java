package pl.pwr.movierental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.ClientData;
import pl.pwr.movierental.service.ClientDataService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ClientDataController {
    @Autowired
    private ClientDataService clientDataService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientData>> getAllClientsData() {
        return clientDataService.getAll();
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createClientData(@RequestBody ClientData clientData) {
        return clientDataService.add(clientData);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<?> getClientDataById(@PathVariable Integer id) {
        return clientDataService.getById(id);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<?> updateClientData(@PathVariable Integer id, @RequestBody ClientData newClientData) {
        return clientDataService.change(id, newClientData);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        return clientDataService.delete(id);
    }
}
