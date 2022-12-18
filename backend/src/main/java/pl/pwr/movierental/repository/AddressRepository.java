package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.movierental.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
