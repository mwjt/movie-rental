package pl.pwr.movierental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.PersonalData;
import pl.pwr.movierental.service.PersonalDataService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PersonalDataController {
    @Autowired
    private PersonalDataService personalDataService;

    @GetMapping("/personals")
    public ResponseEntity<List<PersonalData>> getAllPersonalData() {
        return personalDataService.getAll();
    }

    @PostMapping("/personals")
    public ResponseEntity<?> createPersonalData(@RequestBody PersonalData personalData) {
        return personalDataService.add(personalData);
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<?> getPersonalDataById(@PathVariable Integer id) {
        return personalDataService.getById(id);
    }

    @PutMapping("/personal/{id}")
    public ResponseEntity<?> updatePersonalData(@PathVariable Integer id, @RequestBody PersonalData newPersonalData) {
        return personalDataService.change(id, newPersonalData);
    }

    @DeleteMapping("/personal/{id}")
    public ResponseEntity<?> deletePersonalData(@PathVariable Integer id) {
        return personalDataService.delete(id);
    }
}
