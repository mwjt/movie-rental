package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
