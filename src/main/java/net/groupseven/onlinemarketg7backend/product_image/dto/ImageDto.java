package net.groupseven.onlinemarketg7backend.product_image.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImageDto implements Serializable {
    private long id;

    @Size(max = 50)
    private String name;

    @NotNull
    private String imageUri;

    private long productId;
}
