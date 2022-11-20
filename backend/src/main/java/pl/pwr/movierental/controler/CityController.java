package pl.pwr.movierental.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.City;
import pl.pwr.movierental.repository.CityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @PostMapping("/cities")
    public City createCity(@RequestBody City city) {
        return cityRepository.save(city);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalCity.get());
    }


    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody City newCity) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) return ResponseEntity.notFound().build();

        City city = optionalCity.get();
        city.setCity(newCity.getCity());

        City updatedCity = cityRepository.save(city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCity(@PathVariable Integer id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) return ResponseEntity.notFound().build();

        cityRepository.delete(optionalCity.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
