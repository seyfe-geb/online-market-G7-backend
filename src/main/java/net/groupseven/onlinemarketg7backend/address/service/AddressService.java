package net.groupseven.onlinemarketg7backend.address.service;



import net.groupseven.onlinemarketg7backend.address.dto.AddressDto;
import net.groupseven.onlinemarketg7backend.address.dto.SaveAddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> findAll();

    AddressDto findById(long id);

    AddressDto add(SaveAddressDto dto);

    void deleteById(long id);

    AddressDto updateById(long id, SaveAddressDto dto);
}
