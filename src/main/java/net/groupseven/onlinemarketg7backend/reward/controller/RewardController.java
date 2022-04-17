package net.groupseven.onlinemarketg7backend.reward.controller;


//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.reward.dto.RewardDto;
import net.groupseven.onlinemarketg7backend.reward.dto.SaveRewardDto;
import net.groupseven.onlinemarketg7backend.reward.service.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Users Rewards")
@RestController
@RequestMapping(path = "users-rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService service;

    @GetMapping()
    public List<RewardDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RewardDto get(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping
    public RewardDto add(@RequestBody SaveRewardDto dto){
        return service.add(dto);
    }

    @PutMapping("/{id}")
    public RewardDto update(@PathVariable long id, @RequestBody SaveRewardDto dto){
        return service.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long id){
        service.deleteById(id);
    }
}
