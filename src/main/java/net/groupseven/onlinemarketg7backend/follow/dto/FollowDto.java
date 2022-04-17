package net.groupseven.onlinemarketg7backend.follow.dto;

import com.online.market.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FollowDto {

    private Long id;

    private User follower;

    private User followee;
}
