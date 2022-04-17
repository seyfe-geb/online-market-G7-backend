package net.groupseven.onlinemarketg7backend.review.service;


import net.groupseven.onlinemarketg7backend.review.dto.LightReviewDto;
import net.groupseven.onlinemarketg7backend.review.dto.ReviewDto;
import net.groupseven.onlinemarketg7backend.review.model.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findAll();

    List<Review> findAllByApproved(boolean approved);

    ReviewDto findById(long id);

    LightReviewDto add(LightReviewDto dto);

    void deleteById(long id);

    LightReviewDto updateById(long id, LightReviewDto dto);

    Review approveById(long id);
}
