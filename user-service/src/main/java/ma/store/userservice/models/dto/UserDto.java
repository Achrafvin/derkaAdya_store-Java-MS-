package ma.store.userservice.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String name;
    private  String email;
    private  String imgName;
}
