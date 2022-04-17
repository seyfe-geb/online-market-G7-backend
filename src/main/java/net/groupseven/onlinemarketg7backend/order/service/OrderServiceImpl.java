package net.groupseven.onlinemarketg7backend.order.service;


import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.order.dto.OrderDto;
import net.groupseven.onlinemarketg7backend.order.dto.SaveOrderDto;
import net.groupseven.onlinemarketg7backend.order.model.Order;
import net.groupseven.onlinemarketg7backend.order.model.OrderItem;
import net.groupseven.onlinemarketg7backend.order.model.OrderStatus;
import net.groupseven.onlinemarketg7backend.order.repository.OrderRepository;
import net.groupseven.onlinemarketg7backend.product.model.Product;
import net.groupseven.onlinemarketg7backend.product.repository.ProductRepository;
import net.groupseven.onlinemarketg7backend.reward.model.Reward;
import net.groupseven.onlinemarketg7backend.reward.repository.RewardRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductRepository productRepository;
    private final RewardRepository rewardRepository;

    private final ModelMapper modelMapper;

    private final LoggedInUserService loggedInUserService;
    private final EmailService emailService;
    private final HtmlToPdf htmlToPdf;

    @Override
    public List<OrderDto> findAll() {
        return repository.findAll().stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllByUserId() {
        return repository.findAllByUserId(loggedInUserService.getUserId()).stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findSellerOrders() {
        List<Long> sellerOrdersIds = repository.findSellerOrders(loggedInUserService.getUserId());

        return repository.findAllById(sellerOrdersIds).stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(long id) {
        return modelMapper.map(repository.findById(id).orElse(null), OrderDto.class);
    }

    @Override
    @Transactional
    public OrderDto add(SaveOrderDto dto) throws ValidationException {
        Order order = modelMapper.map(dto, Order.class);

        if (order.getOrderItems().isEmpty())
            throw new ValidationException("Order items cannot be empty");

        double price = 0;

        List<Long> productsId = order.getOrderItems().stream()
                .map(i -> i.getProduct().getId())
                .collect(Collectors.toList());
        List<Product> products = productRepository.findAllById(productsId);

        for (OrderItem orderItem: order.getOrderItems()) {
            Product product = products.stream()
                    .filter(p -> p.getId() == orderItem.getProduct().getId())
                    .findFirst()
                    .get();

            if(orderItem.getQuantity() > product.getQuantity())
                throw new javax.validation.ValidationException("Out of stock item ' " + product.getName() + "" +
                        "' avaliable quantity: '" + product.getQuantity()
                        + "' required quantity: '" + orderItem.getQuantity() + "'");
            orderItem.setPrice(product.getPrice());
            orderItem.setProduct(product);
            price += orderItem.getPrice() * orderItem.getQuantity();

            product.setQuantity(product.getQuantity() - orderItem.getQuantity());
        }

        order.setPrice(price);

        User user = loggedInUserService.getLoggedInUser();
        order.setUser(user);

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setId(Long.valueOf(0));
            orderItem.setOrder(order);
        }

        order = repository.save(order);
        productRepository.saveAll(products);

        OrderDto result = modelMapper.map(order, OrderDto.class);

        Reward reward = new Reward();
        reward.setOrder(order);
        reward.setUser(user);
        reward.setPoints((int)order.getPrice() / 10);
        rewardRepository.save(reward);

        String emailSubject = "your new order: '" + order.getId() +  "' has taken placed";
        String emailMsg = emailSubject + "\n Items: \n";
        emailMsg += order.getOrderItems().stream()
                .map(i -> i.getQuantity() + ": " + i.getProduct().getName() + " - "  + i.getProduct().getPrice() + "\n")
                .collect(Collectors.joining());

        String invoiceUri = "src\\main\\resources\\output\\order-" + order.getId() + "-invoice.pdf";
        //emailService.send(user.getEmail(), emailSubject, emailMsg);
        htmlToPdf.generateInvoicePdf("src\\main\\resources\\templates\\pdf\\invoice.html",
                invoiceUri, result);
        result.setInvoiceUri(invoiceUri);

        return result;
    }

    @Override
    public OrderDto changeStatusById(long id, OrderStatus status) throws ValidationException {
        Order order = repository.getById(id);
        long buyerId = order.getUser().getId();

        if (loggedInUserService.getUserId() != buyerId
                && !order.getOrderItems().stream()
                .anyMatch(i -> i.getProduct().getUser().getId() == loggedInUserService.getUserId())//seller
        )
            throw new ValidationException("This order you are trying to update does not belong to you");

        order.setStatus(status);

        String emailSubject = "Order: " + order.getId() + " status has been changed";
        String emailMsg = emailSubject + "\n" + "New status: " + order.getStatus().name();

        emailMsg += order.getOrderItems().stream()
                .map(i -> i.getQuantity() + ": " + i.getProduct().getName() + " - "  + i.getProduct().getPrice() + "\n")
                .collect(Collectors.joining());

        emailService.send(order.getUser().getEmail(), emailSubject, emailMsg);

        return modelMapper.map(repository.save(order), OrderDto.class);
    }
}
