package ma.store.userservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.store.userservice.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
@Builder

@Table(name = "db_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  String email;
    private  String password;
    private  String imgName;
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role == Role.ADMIN ? Collections.singletonList((GrantedAuthority) () -> "ADMIN") : Collections.singletonList((GrantedAuthority) () -> "USER");
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}