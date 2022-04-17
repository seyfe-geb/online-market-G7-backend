package net.groupseven.onlinemarketg7backend.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.groupseven.onlinemarketg7backend.review.model.Rating;
import net.groupseven.onlinemarketg7backend.user.dto.LightUserDto;

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
