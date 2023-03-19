/*
package com.zebs.facturation;

        import com.zebs.facturation.demandeprix.dao.StudentRepository;
        import com.zebs.facturation.demandeprix.model.entity.Address;
        import com.zebs.facturation.demandeprix.model.entity.Gender;
        import com.zebs.facturation.demandeprix.model.entity.Student;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.boot.SpringApplication;
        import org.springframework.context.annotation.Bean;
        import org.springframework.data.mongodb.core.MongoTemplate;
        import uk.co.jemos.podam.api.PodamFactory;
        import uk.co.jemos.podam.api.PodamFactoryImpl;

        import java.math.BigDecimal;
        import java.time.LocalDateTime;
        import java.util.List;

public class FacAppMongo {
    private PodamFactory podamFactory = new PodamFactoryImpl();

    public static void main(String[] args)  {
        SpringApplication.run(FacturationApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
        return args->{
            Address address = new Address("cameroun", "yaounde", "1925");
            Student student = new Student(
                    "alex", "zebaze", "alexngoumo.an@gmail.com", address, Gender.MALE, List.of("movies, music"),
                    BigDecimal.TEN, LocalDateTime.now()
            );

            String email = "alexngoumo.an@gmail.com";

            repository.findStudentByEmail(email).ifPresentOrElse(s->System.out.println(s + "already exist"),
                    ()->{
                        repository.insert(student);
                        System.out.println("Insert student "+ student);
                    });

			/*Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));
			List<Student> students =  mongoTemplate.find(query, Student.class);
			if(students.size() > 1){
				throw new IllegalStateException("email unique");
			}
			if(students.isEmpty()) {
				repository.insert(student);
				System.out.println("Insert student "+ student);
			}
			else
				System.out.println(student + "already exist");


        };
    }
}*/