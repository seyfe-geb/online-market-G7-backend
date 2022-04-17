package net.groupseven.onlinemarketg7backend.product.service;

import com.online.market.order.model.OrderItem;
import com.online.market.order.repository.OrderItemRepository;
import com.online.market.product.dto.ProductDto;
import com.online.market.product.dto.SaveProductDto;
import com.online.market.product.model.Product;
import com.online.market.product.repository.ProductRepository;
import com.online.market.product_image.dto.ImageDto;
import com.online.market.product_image.model.Image;
import com.online.market.user.model.User;
import com.online.market.util.service.LoggedInUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    private final LoggedInUserService loggedInUserService;

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByUserId() {
        return repository.findAllByUserId(loggedInUserService.getUserId()).stream()
                .map(o -> modelMapper.map(o, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), ProductDto.class);
    }

    @Override
    public ProductDto add(SaveProductDto dto) throws ValidationException {
        Product product = modelMapper.map(dto, Product.class);

        User user = loggedInUserService.getLoggedInUser();

        if(user.isApprovedSeller())
            throw new ValidationException("UnApproved seller can not add products");

        product.setUser(user);

        if(product.getImages() != null && !product.getImages().isEmpty()){
            for (Image productImage: product.getImages())
                productImage.setProduct(product);
        }

        return modelMapper.map(repository.save(product), ProductDto.class);
    }

    @Override
    public ProductDto updateById(long id, SaveProductDto dto) {
        Product product = repository.findById(id).get();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        if(!dto.getImages().isEmpty()){
            product.setImages(product.getImages().stream()
                    .filter(i -> !dto.getImages().stream().anyMatch(g -> g.getId() == i.getId()))
                    .collect(Collectors.toSet()));

            for (ImageDto productImageDto: dto.getImages()) {
                Image image = modelMapper.map(productImageDto, Image.class);
                image.setProduct(product);
                product.getImages().add(image);
            }
        }

        return modelMapper.map(repository.save(product), ProductDto.class);
    }

    @Override
    public void deleteById(long id) throws ValidationException {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(id);

        if (!orderItems.isEmpty())
            throw new ValidationException("Product has already been purchased");

        repository.deleteById(id);
    }
}