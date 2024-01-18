package ma.store.orderservice.web;

import lombok.RequiredArgsConstructor;

import ma.store.orderservice.models.dto.OrderDto;
import ma.store.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ControllerAdvice
public class OrderController {

    private final OrderService orderService;


    @PreAuthorize("hasAuthority('SOCPE_ADMIN')")
    @GetMapping("/orders")
    public List<OrderDto> getAllCarts()
    {
        List<OrderDto> orders = orderService.getAllOrders();
        return orders;
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/order/{id}")
    public OrderDto getOrderById(@PathVariable Long id)
    {
        OrderDto orderDto = orderService.getOrderById(id);
        return orderDto;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto,Authentication authentication){
        Long userId = orderService.getUserIdFromToken(authentication);
        if (!orderDto.getUserId().equals(userId)) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        try {
            orderService.addOrder(orderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/order/{id}")
    public void deleteOrderById(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userOrders")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(Authentication authentication) {
        Long userId = orderService.getUserIdFromToken(authentication);
        List<OrderDto> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/money")
    public double getOrdersMoney()
    {
        return orderService.sumTotalPrice();
    }


}
