package net.groupseven.onlinemarketg7backend.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveRewardDto {
    private long id;
    private long orderId;
    private long userId;
    private int points;
}
