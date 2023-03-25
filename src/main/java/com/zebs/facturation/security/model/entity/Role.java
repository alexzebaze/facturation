package com.zebs.facturation.security.model.entity;

import com.zebs.facturation.model.entity.Base;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "roles")
public class Role extends Base implements GrantedAuthority {
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
