package net.groupseven.onlinemarketg7backend.follow.repository;


import net.groupseven.onlinemarketg7backend.follow.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerIdAndFolloweeId(long followerId, long followeeId);
}
