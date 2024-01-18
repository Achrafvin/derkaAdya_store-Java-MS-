package ma.store.orderservice.web;


import lombok.RequiredArgsConstructor;

import ma.store.orderservice.models.dto.OrderItemDto;
import ma.store.orderservice.services.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/orderitems")
    public List<OrderItemDto> getAllOrderItem()
    {
        List<OrderItemDto> orderItemDtos= orderItemService.getAllOrderItem();
        return orderItemDtos;
    }

    @GetMapping("/orderitem/{id}")
    public OrderItemDto getOrderItemById(@PathVariable Long id)
    {
        OrderItemDto orderItemDto = orderItemService.getOrderItemById(id);
        return orderItemDto;
    }
    @GetMapping("/orders/count")
    public long getOrdersCount()
    {
        return orderItemService.ordersCount();
    }

    @PostMapping("/orderitem")
    public void addOrderItem(@RequestBody OrderItemDto orderItemDto){
        orderItemService.addOrderItem(orderItemDto);
    }

    @DeleteMapping("/orderitem/{id}")
    public void deleteOrderById(@PathVariable("id") Long id){
        orderItemService.deleteOrderItem(id);
    }
}
