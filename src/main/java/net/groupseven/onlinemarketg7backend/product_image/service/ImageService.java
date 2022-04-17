package net.groupseven.onlinemarketg7backend.product_image.service;



import net.groupseven.onlinemarketg7backend.product_image.dto.ImageDto;

import java.util.List;

public interface ImageService {

    List<ImageDto> findAll();

    ImageDto findById(long id);

    ImageDto add(ImageDto dto);

    ImageDto updateById(long id, ImageDto dto);

    void deleteById(long id);
}
