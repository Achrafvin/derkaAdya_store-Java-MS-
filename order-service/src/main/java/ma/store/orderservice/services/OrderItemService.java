package ma.store.orderservice.services;


import ma.store.orderservice.models.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    List<OrderItemDto> getAllOrderItem();

    OrderItemDto getOrderItemById(Long id);

    void addOrderItem(OrderItemDto orderItem);

    void deleteOrderItem(Long id);
    long ordersCount();
}
