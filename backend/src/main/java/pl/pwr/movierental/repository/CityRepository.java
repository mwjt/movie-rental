package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}
