package com.rupeshshrestha.usermanagement.repository;

import com.rupeshshrestha.usermanagement.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    @Query("select role from Role role where role.name = :name")
    Optional<Role> findRoleByName(String name);
}
