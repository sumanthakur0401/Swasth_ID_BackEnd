package com.org.swasth_id_backend.repo;

import com.org.swasth_id_backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Short> {

    Optional<Role> findByRole(String role);
}
