package ma.store.catalogservice.services.product;

import lombok.RequiredArgsConstructor;
import ma.store.catalogservice.mappers.Mapper;
import ma.store.catalogservice.models.dto.ProductDto;
import ma.store.catalogservice.models.entities.Product;
import ma.store.catalogservice.repositories.ProductRepository;
import ma.store.catalogservice.services.category.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final Mapper<Product, ProductDto> mapper;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto getProductByID(Long id) {
        Product product=  productRepository.findById(id).orElseThrow();
        ProductDto productDto=mapper.mapFrom(product);
        return productDto;
    }

    @Override
    public void addProduct(Product product) {
        product.setCategory(categoryService.getCategoryByID(product.getIdCat()));
      productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        // Check if the product with the given ID exists
        Product existingProduct = productRepository.findById(product.getId()).orElseThrow();

        // Update the properties of the existing product
        existingProduct.setName_prod(product.getName_prod());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDesc_prod(product.getDesc_prod());
        existingProduct.setQte_prod(product.getQte_prod());
        existingProduct.setCategory(categoryService.getCategoryByID(product.getIdCat()));
        existingProduct.setImage_prod(product.getImage_prod());

        // Save the updated product. This will perform an update operation because existingProduct has an ID.
        productRepository.save(existingProduct);
    }
    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new RuntimeException("Product Not Found!!!");
        }
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategoryIdCat(id);
    }

    @Override
    public long nbrProducts() {
        return productRepository.count();
    }
}
