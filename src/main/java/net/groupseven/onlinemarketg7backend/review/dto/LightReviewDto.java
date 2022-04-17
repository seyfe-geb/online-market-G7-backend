package net.groupseven.onlinemarketg7backend.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.groupseven.onlinemarketg7backend.review.model.Rating;

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
