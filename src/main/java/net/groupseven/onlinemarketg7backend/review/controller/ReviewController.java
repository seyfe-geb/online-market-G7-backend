package net.groupseven.onlinemarketg7backend.review.controller;

import com.online.market.review.dto.LightReviewDto;
import com.online.market.review.dto.ReviewDto;
import com.online.market.review.service.ReviewService;
import com.online.market.role.model.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reviews")
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
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public List<ReviewDto> getUnapprovedReviews(){
        return reviewService.findAllByApproved(false);
    }

    @PutMapping("/approve-review/{id}")
    public ReviewDto approveReview(@PathVariable("id") long id){
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
