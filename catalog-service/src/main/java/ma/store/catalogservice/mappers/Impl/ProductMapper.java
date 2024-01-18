package ma.store.catalogservice.mappers.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.catalogservice.mappers.Mapper;
import ma.store.catalogservice.models.dto.ProductDto;

import ma.store.catalogservice.models.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final ModelMapper mapper;

    @Override
    public Product mapTo(ProductDto productDto) {
        return mapper.map(productDto , Product.class);
    }

    @Override
    public ProductDto mapFrom(Product product) {
        return mapper.map(product , ProductDto.class);
    }
}
