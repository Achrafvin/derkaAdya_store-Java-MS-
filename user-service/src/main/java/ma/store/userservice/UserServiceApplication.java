package ma.store.userservice;

import ma.store.userservice.config.keysConfig;
import ma.store.userservice.models.entities.User;
import ma.store.userservice.models.enums.Role;
import ma.store.userservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(keysConfig.class)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
          User user = User.builder()
                  .name("Adyataaaaar")
                  .email("adyatar@gmail.com")
                  .password("123456")
                  .imgName("profile.jpg")
                  .role(Role.USER)
                  .build();
          User admin = User.builder()
                  .name("Achraf")
                  .email("ach@gmail.com")
                    .password("123456")
                    .imgName("profile.jpg")
                .role(Role.ADMIN)
                .build();
          User user1 = User.builder()
                  .name("As")
                  .email("as@gmail.com")
                    .password("123456")
                    .imgName("profile.jpg")
                .role(Role.USER)
                .build();
            userService.addUser(admin);
            userService.addUser(user);
            userService.addUser(user1);
        };
    }

}
