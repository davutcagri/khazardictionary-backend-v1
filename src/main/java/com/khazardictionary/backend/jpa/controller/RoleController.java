package com.khazardictionary.backend.jpa.controller;

import com.khazardictionary.backend.jpa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addAdminRole/{username}")
    public void addAdminRole(@PathVariable String username) {
        roleService.addAdminRole(username);
    }

    @DeleteMapping("/deleteAdminRole/{username}")
    public void deleteAdminRole(@PathVariable String username) {
        roleService.deleteAdminRole(username);
    }
}
