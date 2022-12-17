package pl.pwr.movierental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.City;
import pl.pwr.movierental.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        return cityService.getAll();
    }

    @PostMapping("/cities")
    public ResponseEntity<?> createCity(@RequestBody City city) {
        return cityService.add(city);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Integer id) {
        return cityService.getById(id);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody City newCity) {
        return cityService.change(id, newCity);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        return cityService.delete(id);
    }
}
