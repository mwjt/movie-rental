package pl.pwr.movierental.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.ClientData;
import pl.pwr.movierental.repository.ClientDataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class ClientDataController {
    @Autowired
    private ClientDataRepository clientDataRepository;

    @GetMapping("/clients")
    public List<ClientData> getAllClientsData() {
        return clientDataRepository.findAll();
    }

    @PostMapping("/clients")
    public ClientData createClientData(@RequestBody ClientData clientData) {
        return clientDataRepository.save(clientData);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientData> getClientDataById(@PathVariable Integer id) {
        Optional<ClientData> optionalClientData = clientDataRepository.findById(id);
        if (optionalClientData.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalClientData.get());
    }


    @PutMapping("/client/{id}")
    public ResponseEntity<ClientData> updateClientData(@PathVariable Integer id, @RequestBody ClientData newClientData) {
        Optional<ClientData> optionalClientData = clientDataRepository.findById(id);
        if (optionalClientData.isEmpty()) return ResponseEntity.notFound().build();

        ClientData clientData = optionalClientData.get();
        clientData.setCity(newClientData.getCity());
        clientData.setPersonalData(newClientData.getPersonalData());
        clientData.setAccountBalance(newClientData.getAccountBalance());
        clientData.setAddress(newClientData.getAddress());

        ClientData updatedClientData = clientDataRepository.save(clientData);
        return ResponseEntity.ok(updatedClientData);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCity(@PathVariable Integer id) {
        Optional<ClientData> optionalClientData = clientDataRepository.findById(id);
        if (optionalClientData.isEmpty()) return ResponseEntity.notFound().build();

        clientDataRepository.delete(optionalClientData.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
