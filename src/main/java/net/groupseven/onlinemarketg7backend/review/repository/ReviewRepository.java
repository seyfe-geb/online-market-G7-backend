package net.groupseven.onlinemarketg7backend.review.repository;

import com.online.market.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByIsApproved(boolean approved);
}
