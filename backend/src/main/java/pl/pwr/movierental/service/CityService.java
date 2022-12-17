package pl.pwr.movierental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.City;
import pl.pwr.movierental.repository.CityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<List<City>> getAll() {
        List<City> cityList = cityRepository.findAll();
        return ResponseEntity.ok(cityList);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(city.get());
    }

    @Transactional
    public ResponseEntity<?> add(City city) {
        if (city == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        City newCity = cityRepository.saveAndFlush(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCity);
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, City newCity) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newCity.setCityId(id);
        cityRepository.saveAndFlush(newCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCity);
    }

    @Transactional
    public ResponseEntity<?> delete(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        city.ifPresent(cityRepository::delete);
        return ResponseEntity.ok("");
    }
}
