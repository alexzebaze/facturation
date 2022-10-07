package com.zebs.facturation;

import com.zebs.facturation.demandeprix.dao.StudentRepository;
import com.zebs.facturation.demandeprix.model.entity.Address;
import com.zebs.facturation.demandeprix.model.entity.Gender;
import com.zebs.facturation.demandeprix.model.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class FacturationApplication {

	private PodamFactory podamFactory = new PodamFactoryImpl();

	public static void main(String[] args)  {
		SpringApplication.run(FacturationApplication.class, args);
	}

}
