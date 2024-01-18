package ma.store.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private final KeysConfig keysConfig;

    public SecurityConfig(KeysConfig keysConfig) {
        this.keysConfig = keysConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET,"/h2-console/**","/product/**","/category/**","/categories","/products/**").permitAll())
                .headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keysConfig.publicKey()).build();
    }
}
