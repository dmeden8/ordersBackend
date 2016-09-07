package hr.ddcode.cafford;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaffeOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaffeOrdersApplication.class, args);
	}
}
