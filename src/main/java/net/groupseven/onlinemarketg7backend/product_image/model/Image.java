package net.groupseven.onlinemarketg7backend.product_image.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.product.model.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_images")
@Data
@NoArgsConstructor
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(nullable = false, length = 10000)
    private String imageUri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;
}
