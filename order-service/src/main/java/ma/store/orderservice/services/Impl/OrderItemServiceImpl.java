package ma.store.orderservice.services.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.orderservice.clients.ProductServiceClient;
import ma.store.orderservice.mapper.Mapper;
import ma.store.orderservice.models.Entitie.OrderItem;
import ma.store.orderservice.models.dto.OrderItemDto;
import ma.store.orderservice.repositories.OrderItemRepository;
import ma.store.orderservice.services.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final Mapper<OrderItem, OrderItemDto> mapper;
    private final OrderItemRepository orderItemRepository;
    private final ProductServiceClient productServiceClient;
    @Override
    public List<OrderItemDto> getAllOrderItem() {
        List<OrderItem> orderItem=orderItemRepository.findAll();
        orderItem.forEach(prod->prod.setProduct(productServiceClient.getProductById(prod.getProductId())));
        return orderItem.stream().map(mapper::mapFrom).collect(Collectors.toList());
    }


    @Override
    public OrderItemDto getOrderItemById(Long id) {
        OrderItemDto orderItemDto=mapper.mapFrom(orderItemRepository.findById(id).get());
        orderItemDto.setProduct(productServiceClient.getProductById(orderItemDto.getProductId()));
        return orderItemDto;
    }

    @Override
    public void addOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem=mapper.mapTo(orderItemDto);
        orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        if(orderItemRepository.existsById(id))
        {
            orderItemRepository.deleteById(id);
        }
        else throw new RuntimeException("not found!!!");
    }

    @Override
    public long ordersCount() {
        return orderItemRepository.count();
    }
}
