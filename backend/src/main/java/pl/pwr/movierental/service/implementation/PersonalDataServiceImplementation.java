package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.PersonalDataMapper;
import pl.pwr.movierental.model.dto.PersonalDataDTO;
import pl.pwr.movierental.model.entity.PersonalData;
import pl.pwr.movierental.repository.PersonalDataRepository;
import pl.pwr.movierental.service.PersonalDataService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalDataServiceImplementation implements PersonalDataService {

    private final PersonalDataRepository personalDataRepository;
    private final PersonalDataMapper personalDataMapper = PersonalDataMapper.INSTANCE;

    public ResponseEntity<List<PersonalDataDTO>> getAll() {
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        return ResponseEntity.ok(personalDataMapper.personalListToPersonalDTOList(personalDataList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        if (personalData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(personalDataMapper.personalToPersonalDTO(personalData.get()));
    }

    @Transactional
    public ResponseEntity<?> add(PersonalDataDTO personalData) {
        if (personalData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        PersonalData newPersonalData = personalDataRepository.saveAndFlush(personalDataMapper.personalDTOToPersonal(personalData));
        return ResponseEntity.status(HttpStatus.CREATED).body(personalDataMapper.personalToPersonalDTO(newPersonalData));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, PersonalDataDTO newPersonalData) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        if (personalData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newPersonalData.setId(id);
        personalDataRepository.saveAndFlush(personalDataMapper.personalDTOToPersonal(newPersonalData));
        return ResponseEntity.status(HttpStatus.CREATED).body(newPersonalData);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<PersonalData> personalData = personalDataRepository.findById(id);
        personalData.ifPresent(personalDataRepository::delete);
        return ResponseEntity.ok("");
    }
}
