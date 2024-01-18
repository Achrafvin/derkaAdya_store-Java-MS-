package ma.store.userservice.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
}
