package net.groupseven.onlinemarketg7backend.follow.service;


import net.groupseven.onlinemarketg7backend.follow.dto.CreateFollowerDto;
import net.groupseven.onlinemarketg7backend.follow.dto.FollowDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface FollowService {
    List<FollowDto> findAll();

    FollowDto findById(long id);

    FollowDto add(CreateFollowerDto dto);

    void deleteById(long id);

    void unfollow(long followerId, long followeeId) throws ValidationException;
}
