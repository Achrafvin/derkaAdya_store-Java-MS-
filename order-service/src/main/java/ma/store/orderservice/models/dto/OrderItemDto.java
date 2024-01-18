package ma.store.orderservice.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.*;
import ma.store.orderservice.models.Entitie.Order;
import ma.store.orderservice.models.Product;

@Getter@Setter@Builder@NoArgsConstructor@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private int qte;
    private Double price;
    private Product product;
    private Long productId;

}
