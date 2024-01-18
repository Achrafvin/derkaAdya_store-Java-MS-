package ma.store.orderservice.models.dto;

import lombok.*;
import ma.store.orderservice.models.Entitie.OrderItem;

import java.util.Date;
import java.util.Set;

@Getter@Setter@NoArgsConstructor
@AllArgsConstructor@Builder
public class OrderDto {

    private Long id;
    private Set<OrderItem> orderItems;
    private Long userId;
    private Double totalPrice;
    private Date orderDate;

}
