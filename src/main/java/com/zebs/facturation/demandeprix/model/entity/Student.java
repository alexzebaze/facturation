package com.zebs.facturation.demandeprix.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class Student {

    @Id
    private String id;
    private String firstname;
    private String lastname;

    private String email;
    private Address address;
    private Gender gender;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    public Student(String firstname,
                   String lastname,
                   String email,
                   Address address,
                   Gender gender,
                   List<String> favouriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }
}
