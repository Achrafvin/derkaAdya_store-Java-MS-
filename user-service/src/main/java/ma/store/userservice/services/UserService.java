package ma.store.userservice.services;


import ma.store.userservice.models.dto.UserDto;
import ma.store.userservice.models.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAllUsers();

    UserDto getUserByID(Long id);

    Optional<User> getUserByEmail(String email);

    void addUser(User user);
    long nbrUsers();

    Long findUserIdByEmail(String email);

    void updateUser(Long id, UserDto userDto);



}
