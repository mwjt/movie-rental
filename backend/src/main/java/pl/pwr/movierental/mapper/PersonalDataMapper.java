package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.PersonalDataDTO;
import pl.pwr.movierental.model.entity.PersonalData;

import java.util.List;

@Mapper
public interface PersonalDataMapper {
    PersonalDataMapper INSTANCE = Mappers.getMapper(PersonalDataMapper.class);

    PersonalDataDTO personalToPersonalDTO(PersonalData personalData);
    PersonalData personalDTOToPersonal(PersonalDataDTO personalDataDTO);
    List<PersonalDataDTO> personalListToPersonalDTOList(List<PersonalData> personalDataList);
    List<PersonalData> personalDTOListToPersonalList(List<PersonalDataDTO> personalDataDTOList);
}
