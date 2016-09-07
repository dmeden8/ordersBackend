package hr.ddcode.cafford.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "crypto")
@Getter
@Setter
public class CryptoProperties {

    @Value("${crypto.hmac-key}")
    private String hmackey;

}
