package ma.store.orderservice.repositories;


import ma.store.orderservice.models.Entitie.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
long count();

}
