package com.rupeshshrestha.usermanagement.repository;

import com.rupeshshrestha.usermanagement.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User>{

    @Query("select user from User user where user.username = :username")
    Optional<User> findUserByUsername(String username);

    @Query("select user from User user where user.email = :email")
    Optional<User> findUserByEmail(String email);

    @Query("select user from User user where lower(user.firstName) like lower(concat('%', :firstName,'%') )")
    Optional<User> findUserByFirstName(String firstName);

    @Query("select user from User user where lower(user.lastName) like lower(concat('%', :lastName,'%') )")
    Optional<User> findUserByLastName(String lastName);

    @Query("select user from User user where user.email = :username or user.username = :username")
    Optional<User> findByUsernameOrEmail(String username);
}
