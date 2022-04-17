package net.groupseven.onlinemarketg7backend.address.service;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.address.dto.AddressDto;
import net.groupseven.onlinemarketg7backend.address.dto.SaveAddressDto;
import net.groupseven.onlinemarketg7backend.address.model.Address;
import net.groupseven.onlinemarketg7backend.address.repository.AddressRepository;
import net.groupseven.onlinemarketg7backend.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll().stream()
                .map(a -> modelMapper.map(a, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), AddressDto.class);
    }

    @Override
    public AddressDto add(SaveAddressDto dto) {
        Address entity = modelMapper.map(dto, Address.class);
        return modelMapper.map(repository.save(entity), AddressDto.class);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public AddressDto updateById(long id, SaveAddressDto dto) {
        Address entity = modelMapper.map(dto, Address.class);
        entity.setId(id);

        return modelMapper.map(repository.save(entity), AddressDto.class);
    }
}
