package com.khazardictionary.backend.jpa.service;

import com.khazardictionary.backend.jpa.model.Role;
import com.khazardictionary.backend.jpa.repository.RoleRepository;
import com.khazardictionary.backend.network.exception.NotFoundException;
import com.khazardictionary.backend.jpa.model.User;
import com.khazardictionary.backend.jpa.repository.UserRepository;
import com.khazardictionary.backend.jpa.vm.UserUpdateVM;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author davut
 */
@Service
public class UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    FileService fileService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, FileService fileService,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
        this.roleRepository = roleRepository;
    }

    public void save(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        userRole.setUser(user);
        roleRepository.save(userRole);

        if (user.getUsername().equals("davutcagri")) {
            Role adminRole = new Role();
            userRole.setRoleName("ROLE_ADMIN");
            userRole.setUser(user);
            roleRepository.save(adminRole);
        }
    }

    public Page<User> getUsers(Pageable page, User user) {
        if (user != null) {
            return userRepository.findByUsernameNot(user.getUsername(), page);
        }
        return userRepository.findAll(page);
    }

    public User getByUsername(String username) {
        User inDB = userRepository.findByUsername(username);
        if (inDB == null) {
            throw new NotFoundException();
        }
        return inDB;
    }

    public User updateUser(String username, UserUpdateVM updatedUser) {
        User inDB = getByUsername(username);
        inDB.setDisplayName(updatedUser.getDisplayName());
        if (updatedUser.getImage() != null) {
            String oldImageName = inDB.getImage();
            try {
                String storedFileName = fileService.writeBase64EncodedStringToFile(updatedUser.getImage());
                inDB.setImage(storedFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.deleteProfileImage(oldImageName);
        }
        return userRepository.save(inDB);
    }

    public void deleteUser(String username) {
        User inDB = userRepository.findByUsername(username);
        fileService.deleteAllStoreFilesForUser(inDB);
        userRepository.delete(inDB);
    }
}
