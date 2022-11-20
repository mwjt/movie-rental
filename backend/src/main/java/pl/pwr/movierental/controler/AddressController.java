package pl.pwr.movierental.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.Address;
import pl.pwr.movierental.repository.AddressRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @PostMapping("/addresses")
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalAddress.get());
    }


    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address newAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) return ResponseEntity.notFound().build();

        Address address = optionalAddress.get();
        address.setBuildingNumber(newAddress.getBuildingNumber());
        address.setFlatNumber(newAddress.getFlatNumber());
        address.setStreet(newAddress.getStreet());
        address.setPostalCode(newAddress.getPostalCode());

        Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAddress(@PathVariable Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) return ResponseEntity.notFound().build();

        addressRepository.delete(optionalAddress.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
