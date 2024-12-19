package com.spring.JPAApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface JPARepo extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean emailExists(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User WHERE email = :email")
    void deleteUser(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email WHERE u.Id = :Id")
    int updateUser(@Param("email") String email, @Param("Id") Long Id);

    @Modifying
    @Transactional
    @Query("DELETE FROM User")
    int deleteAllUsers();


}
