package net.groupseven.onlinemarketg7backend.reward.service;

import com.online.market.reward.dto.RewardDto;
import com.online.market.reward.dto.SaveRewardDto;
import com.online.market.reward.model.Reward;
import com.online.market.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService{

    private final RewardRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<RewardDto> findAll() {
        return repository.findAll().stream()
                .map(s -> modelMapper.map(s, RewardDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RewardDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), RewardDto.class);
    }

    @Override
    public RewardDto add(SaveRewardDto dto) {
        Reward reward = modelMapper.map(dto, Reward.class);
        return modelMapper.map(repository.save(reward), RewardDto.class);
    }

    @Override
    public RewardDto updateById(long id, SaveRewardDto dto) {
        Reward reward = modelMapper.map(dto, Reward.class);
        reward.setId(id);

        return modelMapper.map(repository.save(reward), RewardDto.class);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
