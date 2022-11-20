package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
