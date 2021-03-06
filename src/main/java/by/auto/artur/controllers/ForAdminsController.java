package by.auto.artur.controllers;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.mapper.UserMapper;
import by.auto.artur.service.AdvertisementService;
import by.auto.artur.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 *
 * Admins can see all information about users
 *
 *
 *@Author ArturVazniak
 */

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class ForAdminsController {

    private final AdvertisementService advertisementService;
    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping("/hello")
    public String hello (Principal principal){
        return "Hello " + principal.getName();
    }

    @GetMapping("/advertisements/all/{isDeleted}")
    public ResponseEntity<List<Advertisement>> allAdvertisements(@PathVariable boolean isDeleted){

        return new ResponseEntity<>(advertisementService.getAllAdvertisement(isDeleted),HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(
                userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") long id){
        User user = userService.getUser(id);

        if(user == null){
            throw new NoSuchContentException("NO user with ID : " + id + " in database");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(String.format("Deletion of user '%s' was successful", user.getUsername()));
    }

    @GetMapping("/users/username/{name}")
    public ResponseEntity<User> ByUsername(@PathVariable String name){
        User user = userService.findUserByUsername(name);

        if(user == null){
            throw new NoSuchContentException("NO user with Name : " + name + " in database");
        }
        return new ResponseEntity<>(
                userService.findUserByUsername(name),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> UserById(@PathVariable(name = "id") long id){
        User user = userService.getUser(id);

        if(user == null){
            throw new NoSuchContentException("NO user with ID : " + id + " in database");
        }
        return new ResponseEntity<>(
                userService.getUser(id), HttpStatus.OK);
    }
}
