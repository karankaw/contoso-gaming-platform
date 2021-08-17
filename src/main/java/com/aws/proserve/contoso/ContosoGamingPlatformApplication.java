package com.aws.proserve.contoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aws.proserve.contoso"})
public class ContosoGamingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContosoGamingPlatformApplication.class, args);
	}

}
