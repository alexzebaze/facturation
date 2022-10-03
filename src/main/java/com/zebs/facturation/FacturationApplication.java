package com.zebs.facturation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootApplication
@EnableSwagger2
public class FacturationApplication implements CommandLineRunner {

	private PodamFactory podamFactory = new PodamFactoryImpl();

	public static void main(String[] args)  {
		SpringApplication.run(FacturationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
