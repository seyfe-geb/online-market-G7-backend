package net.groupseven.onlinemarketg7backend.review.dto;

import com.online.market.review.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LightReviewDto {
    private long id;

    private Rating rating;

    private String comment;

    private long productId;
}
