package ma.store.userservice.services.Impl;

import lombok.RequiredArgsConstructor;
import ma.store.userservice.mappers.Mapper;
import ma.store.userservice.models.dto.UserDto;
import ma.store.userservice.models.entities.User;
import ma.store.userservice.repositories.UserRepository;
import ma.store.userservice.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<User, UserDto> mapper;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserByID(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
       UserDto userDto = mapper.mapFrom(user);
        return userDto;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    public long nbrUsers() {
        return userRepository.count();
    }

    public Long findUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getId();
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }


}
