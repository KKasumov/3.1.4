package ru.kata.spring.rest.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")

    public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "role")
    private String role;

    @Transient
    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUser() {
        return users;
    }

    public void setUser(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRole ( );
    }

    @Override
    public String toString() {
        return role;
    }


    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
        Role role1 = (Role) o;
        return Objects.equals ( id, role1.id ) && Objects.equals ( role, role1.role );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id, role );
    }
}