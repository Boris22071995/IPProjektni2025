package com.etfbl.ip.BackendApp.controller;

import com.etfbl.ip.BackendApp.model.Role;
import com.etfbl.ip.BackendApp.model.User;
import com.etfbl.ip.BackendApp.model.requests.UserRequest;
import com.etfbl.ip.BackendApp.service.RoleService;
import com.etfbl.ip.BackendApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @PostMapping
    public User create(@RequestBody UserRequest userRequest){
        Role role = roleService.findByName(userRequest.getRole());
        User user = new User();
        user.setRole(role);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return userService.save(user);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/user")
    public Optional<User> getByNameAndPassword(@RequestBody UserRequest userRequest){
        return userService.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
    }

}
