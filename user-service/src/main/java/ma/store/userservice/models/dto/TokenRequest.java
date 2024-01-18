package ma.store.userservice.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class TokenRequest {
    private String grantType;
    private String email;
    private String password;
//    private String refreshToken;
//    private boolean withRefreshToken;


}
