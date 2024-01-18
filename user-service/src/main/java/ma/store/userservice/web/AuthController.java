package ma.store.userservice.web;

import lombok.RequiredArgsConstructor;
import ma.store.userservice.models.dto.TokenRequest;
import ma.store.userservice.models.dto.UserRegisterDto;
import ma.store.userservice.models.entities.User;
import ma.store.userservice.models.enums.Role;
import ma.store.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    @Value("$" + "{security.jwt.token.issuer:ADKSecurity}")
    private String issuer;

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> jwtToken(@RequestBody TokenRequest token){
        String sub = null;
        String role=null;
        Long userId = null;
        String grantType = token.getGrantType();
        String email = token.getEmail();
        String password = token.getPassword();

        //String refreshToken = token.getRefreshToken();
        //boolean withRefreshToken = token.isWithRefreshToken();
        if(grantType.equals("password")){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            sub=authentication.getName();
            role=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
            userId = ((User) authentication.getPrincipal()).getId();
        }
//        else if(grantType.equals("refreshToken")){
//            if(refreshToken==null) {
//                return new ResponseEntity<>(Map.of("errorMessage","Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
//            }
//            Jwt decodeJWT = null;
//            try {
//                decodeJWT = jwtDecoder.decode(refreshToken);
//            } catch (JwtException e) {
//                return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()), HttpStatus.UNAUTHORIZED);
//            }
//            subject=decodeJWT.getSubject();
//            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            scope=authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        }
        Map<String, String> idToken=new HashMap<>();
        Instant instant=Instant.now();

        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(sub)
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.MINUTES))
                .issuer(issuer)
                .claim("scope",role)
                .claim("userId",userId)
                .build();
        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccessToken);
//        if(withRefreshToken){
//            JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
//                    .subject(subject)
//                    .issuedAt(instant)
//                    .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
//                    .issuer("security-service")
//                    .build();
//            String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
//            idToken.put("refreshToken",jwtRefreshToken);
//        }
        return new ResponseEntity<>(idToken,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto registrationRequest) {
        if(userService.getUserByEmail(registrationRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User newUser = new User();
        newUser.setName(registrationRequest.getName());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(registrationRequest.getPassword());
        newUser.setRole(Role.USER);
        userService.addUser(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

}
