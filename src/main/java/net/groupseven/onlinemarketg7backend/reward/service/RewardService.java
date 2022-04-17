package net.groupseven.onlinemarketg7backend.reward.service;

import com.online.market.reward.dto.RewardDto;
import com.online.market.reward.dto.SaveRewardDto;

import java.util.List;

public interface RewardService {
    List<RewardDto> findAll();
    RewardDto findById(long id);

    RewardDto add(SaveRewardDto dto);
    RewardDto updateById(long id, SaveRewardDto dto);

    void deleteById(long id);
}
