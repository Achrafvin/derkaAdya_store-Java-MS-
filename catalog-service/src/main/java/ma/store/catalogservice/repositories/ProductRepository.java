package ma.store.catalogservice.repositories;

import ma.store.catalogservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryIdCat(Long id);
    long count();
}
