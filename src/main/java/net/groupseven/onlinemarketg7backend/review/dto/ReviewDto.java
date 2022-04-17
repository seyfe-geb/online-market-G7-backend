package net.groupseven.onlinemarketg7backend.review.dto;

import com.online.market.review.model.Rating;
import com.online.market.user.dto.LightUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private long id;

    private Rating rating;

    private String comment;

    private boolean isApproved;

    private long productId;

    private LightUserDto user;
}
