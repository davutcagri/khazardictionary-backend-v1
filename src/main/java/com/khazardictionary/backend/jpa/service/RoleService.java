package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Role;
import com.khazardictionary.backend.jpa.model.User;
import com.khazardictionary.backend.jpa.repository.RoleRepository;
import com.khazardictionary.backend.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    RoleRepository roleRepository;

    UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void addAdminRole(String username) {
        User user = userRepository.findByUsername(username);
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        role.setUser(user);
        roleRepository.save(role);
    }

    public void deleteAdminRole(String username) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByUserAndRoleName(user, "ROLE_ADMIN");
        roleRepository.deleteById(role.getId());
    }

}
