package ma.store.orderservice.services.Impl;
import lombok.RequiredArgsConstructor;
import ma.store.orderservice.clients.ProductServiceClient;
import ma.store.orderservice.mapper.Mapper;
import ma.store.orderservice.models.Entitie.Order;
import ma.store.orderservice.models.Entitie.OrderItem;
import ma.store.orderservice.models.Product;
import ma.store.orderservice.models.dto.OrderDto;
import ma.store.orderservice.repositories.OrderRepository;
import ma.store.orderservice.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final Mapper<Order, OrderDto> mapper;


    @Override
    public List<OrderDto> getAllOrders() {
       List<Order> orders=orderRepository.findAll();
        orders.forEach(ord->ord.getOrderItems().forEach(orderItem -> {
            Product product = productServiceClient.getProductById(orderItem.getProductId());
            orderItem.setProduct(product);
        }));
      return  orders.stream().map(mapper::mapFrom).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order1 = orderRepository.findById(id).get();
        order1.getOrderItems().forEach(orderItem->orderItem.setProduct(
                productServiceClient.getProductById(orderItem.getProductId())
        ));
        OrderDto orderDto=mapper.mapFrom(orderRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found!!")));
      return orderDto;
    }

    @Override
    public void addOrder(OrderDto orderDto) {
        Order order=mapper.mapTo(orderDto);
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrder(order);
            }
        }
        orderRepository.save(order);

    }

    @Override
    public void deleteOrder(Long id) {
        if(orderRepository.existsById(id))
        {
            orderRepository.deleteById(id);
        }
        else throw new RuntimeException("not found!!!");

    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long id) {
        List<Order> order = orderRepository.findByUserId(id);
        return order.stream().map(mapper::mapFrom).collect(Collectors.toList());
    }

    @Override
    public Double sumTotalPrice() {
        return orderRepository.sumTotalPrice();
    }


    public Long getUserIdFromToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String userIdClaim = "userId";
            return Long.valueOf(jwt.getClaimAsString(userIdClaim));
        }
        return null;
    }

}
