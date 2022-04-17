package net.groupseven.onlinemarketg7backend.order.service;

import com.online.market.order.dto.OrderItemDto;
import com.online.market.order.dto.SaveOrderItemDto;
import com.online.market.order.model.OrderItem;
import com.online.market.order.repository.OrderItemRepository;
import com.online.market.product.model.Product;
import com.online.market.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository repository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderItemDto> findAll() {
        return repository.findAll().stream()
                .map(o -> modelMapper.map(o, OrderItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), OrderItemDto.class);
    }

    @Override
    @Transactional
    public SaveOrderItemDto add(SaveOrderItemDto dto) {
        OrderItem orderItem = modelMapper.map(dto, OrderItem.class);
        Product product = productRepository.getById(dto.getProductId());

        if(dto.getQuantity() == 0)
            dto.setQuantity(1);

        if(dto.getQuantity() > product.getQuantity())
            throw new ValidationException("Out of stock item ' " + product.getName() + "" +
                    "' avaliable quantity: '" + product.getQuantity()
                    + "' required quantity: '" + dto.getQuantity() + "'");

        orderItem.setPrice(product.getPrice() * dto.getQuantity());
        product.setQuantity(product.getQuantity() - dto.getQuantity());

        orderItem = repository.save(orderItem);
        productRepository.save(product);

        return modelMapper.map(orderItem , SaveOrderItemDto.class);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
