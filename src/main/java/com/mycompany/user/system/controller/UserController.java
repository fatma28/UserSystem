package com.mycompany.user.system.controller;

import com.mycompany.user.system.model.Response;
import com.mycompany.user.system.model.User;
import com.mycompany.user.system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@RequestParam(name = "civilId") int civilId) {
        return userService.getUserInfo(civilId);
    }

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> userIterable = userService.getAllUsers();
        return new ResponseEntity<>(userIterable, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addUser(@Validated @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<User> deleteUser(@RequestParam(name = "civilId") int civilId) {
        userService.deleteUser(civilId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
