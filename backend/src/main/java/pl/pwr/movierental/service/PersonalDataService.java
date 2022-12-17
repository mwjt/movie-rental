package pl.pwr.movierental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.PersonalData;
import pl.pwr.movierental.repository.PersonalDataRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalDataService {

    @Autowired
    private PersonalDataRepository personalDataRepository;

    public ResponseEntity<List<PersonalData>> getAll() {
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        return ResponseEntity.ok(personalDataList);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        if (personalData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(personalData.get());
    }

    @Transactional
    public ResponseEntity<?> add(PersonalData personalData) {
        if (personalData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        PersonalData newPersonalData = personalDataRepository.saveAndFlush(personalData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersonalData);
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, PersonalData newPersonalData) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        if (personalData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newPersonalData.setPersonalDataId(id);
        personalDataRepository.saveAndFlush(newPersonalData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersonalData);
    }

    @Transactional
    public ResponseEntity<?> delete(Integer id) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        personalData.ifPresent(personalDataRepository::delete);
        return ResponseEntity.ok("");
    }

    public PersonalData getByLogin(String login) {
        Optional<PersonalData> personalData = personalDataRepository.findByLogin(login);
        return personalData.orElse(null);
    }
}
