package com.zebs.facturation.security.model.entity;

import com.zebs.facturation.model.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "users_roles")
public class UserRole extends Base {
    private String role;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;
}
