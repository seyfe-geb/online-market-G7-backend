package net.groupseven.onlinemarketg7backend.product_image.controller;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.product_image.dto.ImageDto;
import net.groupseven.onlinemarketg7backend.product_image.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Product Images")
@RestController
@RequestMapping("/product-images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageDto> getAll(){
        return imageService.findAll();
    }

    @GetMapping("/{id}")
    public ImageDto get(@PathVariable("id") long id){
        return imageService.findById(id);
    }

    @PostMapping
    public ImageDto add(@RequestBody ImageDto dto){
        return imageService.add(dto);
    }

    @PutMapping("/{id}")
    public ImageDto update(@PathVariable("id") long id, @RequestBody ImageDto dto){
        return imageService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        imageService.deleteById(id);
    }
}
