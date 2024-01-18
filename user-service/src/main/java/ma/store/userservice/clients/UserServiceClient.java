package ma.store.userservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface UserServiceClient {

//    @GetMapping("/user/{id}")
//    @CircuitBreaker(name = "userService",fallbackMethod = "getDefaultUser")
//    User getUserById(@PathVariable("id") Long id);
//
//    @GetMapping("/users")
//    List<User> getAllUsers();
//
//    default User getDefaultUser(Long id, Exception e){
//        User userDto = new User();
//        userDto.setName("Not Availbale");
//        userDto.setEmail("Not Availbale");
//        userDto.setAddress("Not Availbale");
//        return userDto;
//    }

}
