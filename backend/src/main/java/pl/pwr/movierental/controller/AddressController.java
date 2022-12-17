package pl.pwr.movierental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.Address;
import pl.pwr.movierental.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return addressService.getAll();
    }

    @PostMapping("/addresses")
    public ResponseEntity<?> createAddress(@RequestBody Address address) {
        return addressService.add(address);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Integer id) {
        return addressService.getById(id);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Integer id, @RequestBody Address newAddress) {
        return addressService.change(id, newAddress);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
        return addressService.delete(id);
    }
}
