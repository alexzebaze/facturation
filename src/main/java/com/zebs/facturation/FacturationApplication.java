package com.zebs.facturation;

import com.zebs.facturation.commun.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
		FileUploadProperties.class,
})
public class FacturationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturationApplication.class, args);
	}

}
