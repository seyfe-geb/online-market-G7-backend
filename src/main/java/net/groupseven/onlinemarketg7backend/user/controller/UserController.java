package net.groupseven.onlinemarketg7backend.user.controller;

import com.online.market.user.dto.*;
import com.online.market.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users")
@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping()
    public List<UserDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    public UserDto add(@RequestBody CreateUserDto dto){
        return service.add(dto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable long id, @RequestBody LightUserDto dto){
        return service.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long id){
        service.deleteById(id);
    }

    @GetMapping("/get-unapproved-sellers")
    public List<UnApprovedSellerDto> getUnapprovedSellers(){
        return service.findSellersByApproved(false);
    }

    @PutMapping("/approve-seller/{id}")
    public UserDto approveSeller(@PathVariable("id") long id){
        return service.approveById(id);
    }

    @GetMapping("/profile")
    public UserProfileDto profile() {
        return service.findProfile();
    }

    @GetMapping("/profile/{id}")
    public UserDto getUserProfile(@PathVariable long id) {
        return service.findById(id);
    }
}
