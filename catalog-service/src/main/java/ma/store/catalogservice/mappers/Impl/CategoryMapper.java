package ma.store.catalogservice.mappers.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.catalogservice.mappers.Mapper;
import ma.store.catalogservice.models.dto.CategoryDto;
import ma.store.catalogservice.models.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private final ModelMapper mapper;
    @Override
    public Category mapTo(CategoryDto categoryDto) {
        return mapper.map(categoryDto,Category.class);
    }

    @Override
    public CategoryDto mapFrom(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
