package com.rupeshshrestha.usermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class For Role
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ROLE")
public class Role extends IdentifiableEntity{

    @Column(name = "NAME", unique = true)
    private String name;
}
