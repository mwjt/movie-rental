package pl.pwr.movierental.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.PersonalData;
import pl.pwr.movierental.repository.PersonalDataRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class PersonalDataController {
    @Autowired
    private PersonalDataRepository personalDataRepository;

    @GetMapping("/personals")
    public List<PersonalData> getAllPersonalData() {
        return personalDataRepository.findAll();
    }

    @PostMapping("/personals")
    public PersonalData createPersonalData(@RequestBody PersonalData personalData) {
        return personalDataRepository.save(personalData);
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<PersonalData> getPersonalDataById(@PathVariable Integer id) {
        Optional<PersonalData> optionalPersonalData = personalDataRepository.findById(id);
        if (optionalPersonalData.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalPersonalData.get());
    }


    @PutMapping("/personal/{id}")
    public ResponseEntity<PersonalData> updatePersonalData(@PathVariable Integer id, @RequestBody PersonalData newPersonalData) {
        Optional<PersonalData> optionalPersonalData = personalDataRepository.findById(id);
        if (optionalPersonalData.isEmpty()) return ResponseEntity.notFound().build();

        PersonalData personalData = optionalPersonalData.get();
        personalData.setEmail(newPersonalData.getEmail());
        personalData.setLogin(newPersonalData.getLogin());
        personalData.setName(newPersonalData.getName());
        personalData.setSurname(newPersonalData.getSurname());
        personalData.setPhoneNumber(newPersonalData.getPhoneNumber());
        personalData.setPassword(newPersonalData.getPassword());

        PersonalData updatedPersonalData = personalDataRepository.save(personalData);
        return ResponseEntity.ok(updatedPersonalData);
    }

    @DeleteMapping("/personal/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePersonalData(@PathVariable Integer id) {
        Optional<PersonalData> optionalPersonalData = personalDataRepository.findById(id);
        if (optionalPersonalData.isEmpty()) return ResponseEntity.notFound().build();

        personalDataRepository.delete(optionalPersonalData.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
