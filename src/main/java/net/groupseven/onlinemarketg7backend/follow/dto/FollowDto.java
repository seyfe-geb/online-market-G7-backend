package net.groupseven.onlinemarketg7backend.follow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.user.model.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowDto {

    private Long id;

    private User follower;

    private User followee;
}
