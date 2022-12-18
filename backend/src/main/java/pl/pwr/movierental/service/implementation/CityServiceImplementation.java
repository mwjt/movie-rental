package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.CityMapper;
import pl.pwr.movierental.model.dto.CityDTO;
import pl.pwr.movierental.model.entity.City;
import pl.pwr.movierental.repository.CityRepository;
import pl.pwr.movierental.service.CityService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImplementation implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper = CityMapper.INSTANCE;

    public ResponseEntity<List<CityDTO>> getAll() {
        List<City> cityList = cityRepository.findAll();
        return ResponseEntity.ok(cityMapper.cityListToCityDTOList(cityList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(cityMapper.cityToCityDTO(city.get()));
    }

    @Transactional
    public ResponseEntity<?> add(CityDTO city) {
        if (city == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        City newCity = cityRepository.saveAndFlush(cityMapper.cityDTOToCity(city));
        return ResponseEntity.status(HttpStatus.CREATED).body(cityMapper.cityToCityDTO(newCity));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, CityDTO newCity) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newCity.setId(id);
        cityRepository.saveAndFlush(cityMapper.cityDTOToCity(newCity));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCity);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<City> city = cityRepository.findById(id);
        city.ifPresent(cityRepository::delete);
        return ResponseEntity.ok("");
    }
}
