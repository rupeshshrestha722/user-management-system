package com.rupeshshrestha.usermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity Class for User
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends IdentifiableEntity {
    @Column(name="USERNAME", unique = true)
    private String username;

    @Column(name ="PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name="EMAIL", unique = true)
    @Email
    private String email;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dob;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> role = new HashSet<>();

}
