package ma.store.orderservice.services;


import ma.store.orderservice.models.dto.OrderDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    void addOrder(OrderDto orderDto);

    void deleteOrder(Long id);




    Double sumTotalPrice();

    List<OrderDto> getOrdersByUserId(Long id);

    Long getUserIdFromToken(Authentication authentication);


}
