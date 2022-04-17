package net.groupseven.onlinemarketg7backend.review.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.online.market.product.model.Product;
import com.online.market.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.groupseven.onlinemarketg7backend.user.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_reviews")
@Setter
@Getter
@NoArgsConstructor
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Rating rating;

    private String comment;

    @Column(name = "is_approved")
    private boolean isApproved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;
}
