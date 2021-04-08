package com.example.uploadingfiles.repositories;

import com.example.uploadingfiles.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select count (u) from User u where u.login= :login")
    long countUsersByLogin(String login);

    User findUserByLogin(String login);
}
