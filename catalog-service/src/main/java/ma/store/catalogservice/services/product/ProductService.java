package ma.store.catalogservice.services.product;



import ma.store.catalogservice.models.dto.ProductDto;
import ma.store.catalogservice.models.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();

    ProductDto getProductByID(Long id);

    void addProduct(Product product);

    void deleteProduct(Long id);
    void updateProduct(Product product);

    List<Product> getProductsByCategoryId(Long id);
    long nbrProducts();



}
