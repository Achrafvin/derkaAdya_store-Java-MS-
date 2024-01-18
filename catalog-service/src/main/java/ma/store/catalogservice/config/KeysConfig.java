package ma.store.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record KeysConfig(RSAPublicKey publicKey) {
}
