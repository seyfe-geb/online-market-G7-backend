package net.groupseven.onlinemarketg7backend.reward.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.groupseven.onlinemarketg7backend.order.dto.OrderDto;
import net.groupseven.onlinemarketg7backend.user.dto.LightUserDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RewardDto {
    private long id;

    private OrderDto order;

    private LightUserDto user;
}
