package net.groupseven.onlinemarketg7backend.reward.dto;

import com.online.market.order.dto.OrderDto;
import com.online.market.user.dto.LightUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RewardDto {
    private long id;

    private OrderDto order;

    private LightUserDto user;
}
