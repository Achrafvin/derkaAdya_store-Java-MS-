package ma.store.cartservice.repositories;

import ma.store.cartservice.models.Entitie.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByCart_Id(Long id);
}
