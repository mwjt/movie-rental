package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.AddressMapper;
import pl.pwr.movierental.model.dto.AddressDTO;
import pl.pwr.movierental.model.entity.Address;
import pl.pwr.movierental.repository.AddressRepository;
import pl.pwr.movierental.service.AddressService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    public ResponseEntity<List<AddressDTO>> getAll() {
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok(addressMapper.addressListToAddressDTOList(addressList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(addressMapper.addressToAddressDTO(address.get()));
    }

    @Transactional
    public ResponseEntity<?> add(AddressDTO address) {
        if (address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Address newAddress = addressRepository.saveAndFlush(addressMapper.addressDTOToAddress(address));
        return ResponseEntity.status(HttpStatus.CREATED).body(addressMapper.addressToAddressDTO(newAddress));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, AddressDTO newAddress) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newAddress.setId(id);
        addressRepository.saveAndFlush(addressMapper.addressDTOToAddress(newAddress));
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        address.ifPresent(addressRepository::delete);
        return ResponseEntity.ok("");
    }
}
