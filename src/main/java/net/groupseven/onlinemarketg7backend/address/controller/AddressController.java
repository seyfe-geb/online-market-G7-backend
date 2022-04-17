package net.groupseven.onlinemarketg7backend.address.controller;

import com.online.market.address.dto.AddressDto;
import com.online.market.address.dto.SaveAddressDto;
import com.online.market.address.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Addresses")
@RestController
@RequestMapping(path = "user-addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @GetMapping()
    public List<AddressDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AddressDto get(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    public AddressDto add(@RequestBody SaveAddressDto dto){
        return service.add(dto);
    }

    @PutMapping("/{id}")
    public AddressDto update(@PathVariable("id") long id, @RequestBody SaveAddressDto dto){
        return service.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") long id){
        service.deleteById(id);
    }
}
