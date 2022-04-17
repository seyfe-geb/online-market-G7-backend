package net.groupseven.onlinemarketg7backend.review.controller;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.review.dto.LightReviewDto;
import net.groupseven.onlinemarketg7backend.review.dto.ReviewDto;
import net.groupseven.onlinemarketg7backend.review.model.Review;
import net.groupseven.onlinemarketg7backend.review.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Reviews")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAll(){
        return reviewService.findAll();
    }

    @GetMapping("/get-unapproved-reviews")
//    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public List<Review> getUnapprovedReviews(){
        return reviewService.findAllByApproved(false);
    }

    @PutMapping("/approve-review/{id}")
    public Review approveReview(@PathVariable("id") long id){
        return reviewService.approveById(id);
    }

    @GetMapping("/{id}")
    public ReviewDto get(@PathVariable("id") long id){
        return reviewService.findById(id);
    }

    @PostMapping
    public LightReviewDto add(@RequestBody LightReviewDto dto){
        return reviewService.add(dto);
    }

    @PutMapping("/{id}")
    public LightReviewDto update(@PathVariable("id") long id, @RequestBody LightReviewDto dto){
        return reviewService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        reviewService.deleteById(id);
    }
}
