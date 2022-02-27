package br.com.amaro.SIF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SifApplication {

	public static void main(String[] args) {
		SpringApplication.run(SifApplication.class, args);
	}

}
