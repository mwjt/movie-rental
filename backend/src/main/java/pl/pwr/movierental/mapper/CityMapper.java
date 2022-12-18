package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.CityDTO;
import pl.pwr.movierental.model.entity.City;

import java.util.List;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO cityToCityDTO(City city);
    City cityDTOToCity(CityDTO cityDTO);
    List<CityDTO> cityListToCityDTOList(List<City> cityList);
    List<City> cityDTOListToCityList(List<CityDTO> cityDTOList);
}
