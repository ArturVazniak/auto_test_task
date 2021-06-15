package by.auto.artur.controllers;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.mapper.UserMapper;
import by.auto.artur.service.AdvertisementService;
import by.auto.artur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@RestController
@RequestMapping("/api/admin")
public class ForAdminsController {

    private final AdvertisementService advertisementService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public ForAdminsController(AdvertisementService advertisementService,
                               UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.advertisementService = advertisementService;
        this.userMapper = userMapper;
    }

    @GetMapping("/hello")
    public String hello (Principal principal){

        return "Hello " + principal.getName();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> allUsers(){

        return new ResponseEntity<>(userMapper.userListToDto(
                userService.getAllUsers()), HttpStatus.OK);
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
    public ResponseEntity<UserDto> ByUsername(@PathVariable String name){
        User user = userService.findUserByUsername(name);

        if(user == null){
            throw new NoSuchContentException("NO user with Name : " + name + " in database");
        }
        return new ResponseEntity<>(userMapper.userToDto(
                userService.findUserByUsername(name)),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> UserById(@PathVariable(name = "id") long id){
        User user = userService.getUser(id);

        if(user == null){
            throw new NoSuchContentException("NO user with ID : " + id + " in database");
        }
        return new ResponseEntity<>(userMapper.userToDto(
                userService.getUser(id)), HttpStatus.OK);
    }

}
