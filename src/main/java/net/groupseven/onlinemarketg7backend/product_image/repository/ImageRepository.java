package net.groupseven.onlinemarketg7backend.product_image.repository;

import com.online.market.product_image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}