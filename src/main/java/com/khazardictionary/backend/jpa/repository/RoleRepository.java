package com.khazardictionary.backend.jpa.repository;

import com.khazardictionary.backend.jpa.model.Role;
import com.khazardictionary.backend.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByUserAndRoleName(User user, String roleName);

}
