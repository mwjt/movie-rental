package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.ClientDataMapper;
import pl.pwr.movierental.model.dto.ClientDataDTO;
import pl.pwr.movierental.model.entity.ClientData;
import pl.pwr.movierental.repository.ClientDataRepository;
import pl.pwr.movierental.service.ClientDataService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientDataServiceImplementation implements ClientDataService {

    private final ClientDataRepository clientDataRepository;
    private final ClientDataMapper clientDataMapper = ClientDataMapper.INSTANCE;

    public ResponseEntity<List<ClientDataDTO>> getAll() {
        List<ClientData> clientDataList = clientDataRepository.findAll();
        return ResponseEntity.ok(clientDataMapper.clientListToClientDTOList(clientDataList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(clientDataMapper.clientToClientDTO(clientData.get()));
    }

    @Transactional
    public ResponseEntity<?> add(ClientDataDTO clientData) {
        if (clientData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        ClientData newClientData = clientDataRepository.saveAndFlush(clientDataMapper.clientDTOToClient(clientData));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDataMapper.clientToClientDTO(newClientData));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, ClientDataDTO newClientData) {
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newClientData.setClientId(id);
        clientDataRepository.saveAndFlush(clientDataMapper.clientDTOToClient(newClientData));
        return ResponseEntity.status(HttpStatus.CREATED).body(newClientData);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        clientData.ifPresent(clientDataRepository::delete);
        return ResponseEntity.ok("");
    }
}
