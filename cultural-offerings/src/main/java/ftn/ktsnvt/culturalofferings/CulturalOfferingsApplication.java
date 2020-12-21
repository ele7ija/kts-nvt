package ftn.ktsnvt.culturalofferings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CulturalOfferingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CulturalOfferingsApplication.class, args);
	}

}
