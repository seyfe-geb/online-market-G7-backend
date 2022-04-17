package net.groupseven.onlinemarketg7backend.order.service;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.order.dto.OrderItemDto;
import net.groupseven.onlinemarketg7backend.order.dto.SaveOrderItemDto;
import net.groupseven.onlinemarketg7backend.order.model.OrderItem;
import net.groupseven.onlinemarketg7backend.order.repository.OrderItemRepository;
import net.groupseven.onlinemarketg7backend.product.model.Product;
import net.groupseven.onlinemarketg7backend.product.repository.ProductRepository;
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
