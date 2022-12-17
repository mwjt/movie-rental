package pl.pwr.movierental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.Address;
import pl.pwr.movierental.repository.AddressRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<List<Address>> getAll() {
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok(addressList);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(address.get());
    }

    @Transactional
    public ResponseEntity<?> add(Address address) {
        if (address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Address newAddress = addressRepository.saveAndFlush(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, Address newAddress) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newAddress.setAddressId(id);
        addressRepository.saveAndFlush(newAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @Transactional
    public ResponseEntity<?> delete(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        address.ifPresent(addressRepository::delete);
        return ResponseEntity.ok("");
    }
}
