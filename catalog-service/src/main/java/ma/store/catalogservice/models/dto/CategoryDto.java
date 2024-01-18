package ma.store.catalogservice.models.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    private String name_cat;
    private  String img_name;
}

