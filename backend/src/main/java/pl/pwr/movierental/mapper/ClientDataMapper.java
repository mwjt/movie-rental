package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.ClientDataDTO;
import pl.pwr.movierental.model.entity.ClientData;

import java.util.List;

@Mapper
public interface ClientDataMapper {
    ClientDataMapper INSTANCE = Mappers.getMapper(ClientDataMapper.class);

    ClientDataDTO clientToClientDTO(ClientData clientData);
    ClientData clientDTOToClient(ClientDataDTO clientDataDTO);
    List<ClientDataDTO> clientListToClientDTOList(List<ClientData> clientDataList);
    List<ClientData> clientDTOListToClientList(List<ClientDataDTO> clientDataDTOList);
}
