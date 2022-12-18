package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.AddressDTO;
import pl.pwr.movierental.model.entity.Address;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDTO(Address address);
    Address addressDTOToAddress(AddressDTO addressDTO);
    List<AddressDTO> addressListToAddressDTOList(List<Address> addressList);
    List<Address> addressDTOListToAddressList(List<AddressDTO> addressDTOList);
}
