package by.auto.artur.controllers;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.AdvertisementService;
import by.auto.artur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    public ForAdminsController(AdvertisementService advertisementService, UserService userService) {
        this.userService = userService;
        this.advertisementService = advertisementService;

    }

    @GetMapping("/advertisements/role/{role}")
    public List<Advertisement> allAdvertisements(@PathVariable boolean role){

        return advertisementService.getAllAdvertisement(role);
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

}
