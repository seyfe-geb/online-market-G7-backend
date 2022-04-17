package net.groupseven.onlinemarketg7backend.review.service;

import com.online.market.review.dto.LightReviewDto;
import com.online.market.review.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findAll();

    List<ReviewDto> findAllByApproved(boolean approved);

    ReviewDto findById(long id);

    LightReviewDto add(LightReviewDto dto);

    void deleteById(long id);

    LightReviewDto updateById(long id, LightReviewDto dto);

    ReviewDto approveById(long id);
}
