package net.groupseven.onlinemarketg7backend.product.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.online.market.product_image.dto.ImageDto;
import com.online.market.review.dto.ReviewDto;
import com.online.market.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto implements Serializable {
    private long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private double price;

    @NotNull
    private String description;

    @NotNull
    private int quantity;

    private UserDto user;

    @JsonManagedReference
    private List<ImageDto> images;
    @JsonManagedReference
    private List<ReviewDto> reviews;
}