package ma.store.cartservice.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfiguration  {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getCredentials() instanceof String) {
                String currentToken = (String) authentication.getCredentials();
                requestTemplate.header("Authorization", "Bearer " + currentToken);
            }
        };
    }
}
