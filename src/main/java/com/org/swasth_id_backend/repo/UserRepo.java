package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<com.org.swasth_id_backend.entity.User, Short>{

    Optional<com.org.swasth_id_backend.entity.User> findByUsername(String username);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
