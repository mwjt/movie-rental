package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.ClientData;

public interface ClientDataRepository extends JpaRepository<ClientData, Integer> {
}
