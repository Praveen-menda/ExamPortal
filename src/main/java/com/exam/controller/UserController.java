package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @PostMapping("/add")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRole("USER");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        roles.add(userRole);
        user.setPassword(encoder.encode(user.getPassword()));
        return this.userService.createUser(user,roles);
    }

    @GetMapping("/{username}")
    public User getUserByUserName(@PathVariable ("username") String username)
    {
        User user = userService.getByUsername(username);
        return user;
    }
    @DeleteMapping("/{userid}")
    public void deleteById(@PathVariable ("userid") Long id)
    {
        userService.deleteById(id);
    }
    // add for update

}
