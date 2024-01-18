package ma.store.userservice.web;


import lombok.RequiredArgsConstructor;
import ma.store.userservice.models.dto.UserDto;
import ma.store.userservice.models.entities.User;
import ma.store.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

private final UserService userService;

    @GetMapping("/users/nbr")
    public long nbrUsers()
    {
        return userService.nbrUsers();

    }
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@GetMapping("/users")
    public List<User> getAllUsers()
    {
      return userService.getAllUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable Long id)
    {
        return userService.getUserByID(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/user")
    public void saveUser(@RequestBody User user)
    {
        userService.addUser(user);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/profile")
    public Long getCurrentUserId(Authentication authentication) {
        return userService.findUserIdByEmail(authentication.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserDto userDto,
                                        Authentication authentication) {
        Long currentUserId = userService.findUserIdByEmail(authentication.getName());
        if (!currentUserId.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        userService.updateUser(id, userDto);
        return ResponseEntity.ok("{\"message\": \"User updated successfully\"}");
    }




}
