package ma.store.cartservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.store.cartservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/user/{id}")
    @CircuitBreaker(name = "userService",fallbackMethod = "getDefaultUser")
    User getUserById(@PathVariable("id") Long id);

    default User getDefaultUser(Long id, Exception e){
        User userDto = new User();
        userDto.setId(null);
        return userDto;
    }

}
