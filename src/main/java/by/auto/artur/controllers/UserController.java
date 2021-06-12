package by.auto.artur.controllers;

import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> allUsers(){

        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id){

        long longId = Integer.parseInt(id);
        User user = userService.getUser(longId);

        if(user == null){
            throw new NoSuchContentException("NO user with ID : " + longId + " in database");
        }
        userService.deleteUser(longId);
    }

    @GetMapping("/users/username/{name}")
    public User ByUsername(@PathVariable String name){

        User user = userService.findUserByUsername(name);
        if(user == null){
            throw new NoSuchContentException("NO user with Name : " + name + " in database");
        }
        return userService.findUserByUsername(name);
    }

    @GetMapping("/users/{id}")
    public User UserById(@PathVariable String id){

        long longId = Integer.parseInt(id);
        User user = userService.getUser(longId);

        if(user == null){
            throw new NoSuchContentException("NO user with ID : " + longId + " in database");
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<String> registrationUser(@Valid @RequestBody User user){

        userService.saveUser(user);

        return ResponseEntity.ok("User is valid");
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user){

        userService.saveUser(user);

        return ResponseEntity.ok("User is valid");
    }
}
