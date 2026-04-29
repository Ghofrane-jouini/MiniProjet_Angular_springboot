package com.ghofrane.users;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ghofrane.users.entities.Role;
import com.ghofrane.users.entities.User;
import com.ghofrane.users.service.UserService;

import jakarta.annotation.PostConstruct;
@SpringBootApplication
public class UsersMicroservicesApplication {

    @Autowired
    UserService userService;
/*
    @PostConstruct
    void init_users() {

        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "USER"));

        userService.saveUser(new User(null, "admin", "123", true, null));
        userService.saveUser(new User(null, "ghofrane", "123", true, null));
        userService.saveUser(new User(null,"yomna","123",true,null));

        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("yomna", "USER");
        userService.addRoleToUser("ghofrane", "USER");
    }*/

    public static void main(String[] args) {
        SpringApplication.run(UsersMicroservicesApplication.class, args);
    }
}