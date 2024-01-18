package ma.store.userservice.services.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.userservice.models.entities.User;
import ma.store.userservice.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userEntity = userService.getUserByEmail(email);
        return userEntity.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }


}
