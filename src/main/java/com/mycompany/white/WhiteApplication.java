package com.mycompany.white;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource(value = {"classpath:image.properties"})
public class WhiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteApplication.class, args);
	}

}
