package hr.ddcode.cafford.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableConfigurationProperties({ CryptoProperties.class })
public class CryptoConfig {

    @Autowired
    private CryptoProperties cryptoProperties;

    /*
    @Bean
    public Hmac hmacEncoder() {
        return new Hmac(cryptoProperties.getHmackey());
    }
    */

    @Bean
    public BCryptPasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }

}
