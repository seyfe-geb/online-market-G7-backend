package net.groupseven.onlinemarketg7backend.reward.repository;


import net.groupseven.onlinemarketg7backend.reward.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
}
