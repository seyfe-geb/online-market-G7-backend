package net.groupseven.onlinemarketg7backend.follow.controller;

import com.online.market.follow.dto.CreateFollowerDto;
import com.online.market.follow.dto.FollowDto;
import com.online.market.follow.service.FollowService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@Tag(name = "User Follows")
@RestController
@RequestMapping(path = "user-follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService service;

    @GetMapping()
    public List<FollowDto> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FollowDto get(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    public FollowDto add(@RequestBody CreateFollowerDto dto){
        return service.add(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") long id){
        service.deleteById(id);
    }

    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void unfollow(@PathVariable long followerId, @PathVariable long followeeId) throws ValidationException {
        service.unfollow(followerId, followeeId);
    }
}
