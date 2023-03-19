package com.zebs.facturation.security;


import com.zebs.facturation.model.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "articles")
public class User extends Base {
    private String email;
    private String username;
    private String nom;
    private String prenom;
    private String password;
    
}
