package by.auto.artur.controllers;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.AdvertisementService;
import by.auto.artur.service.UserService;
import by.auto.artur.service.implemintation.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@RestController
@RequestMapping("/api/user")
public class ForAuthenticatedUsersController {

    private final AdvertisementService advertisementService;
    private final UserService userService;
    private final UserSecurityService userSecurityService;

    @Autowired
    public ForAuthenticatedUsersController(AdvertisementService advertisementService,
                                           UserService userService,
                                           UserSecurityService userSecurityService) {

        this.advertisementService = advertisementService;
        this.userService = userService;
        this.userSecurityService = userSecurityService;
    }

    @GetMapping("/hello")
    public String hello (Principal principal){

        return "Hello " + principal.getName();
    }

    @PostMapping("/advertisements")
    public ResponseEntity<String> addNewAdvertisement(@Valid @RequestBody Advertisement advertisement){

        advertisementService.saveAdvertisement(advertisement);

        return ResponseEntity.ok("Advertisement is valid");
    }

    @PutMapping("/advertisements")
    public ResponseEntity<String> updateAdvertisement(@Valid @RequestBody Advertisement advertisement){

        advertisementService.saveAdvertisement(advertisement);

        return ResponseEntity.ok("Advertisement is valid");
    }

    @DeleteMapping("/advertisements/{id}")
    public void deleteAdvertisement(@PathVariable String id) {

        long longId = Integer.parseInt(id);
        advertisementService.deleteAdvertisement(longId);
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user){

        userService.saveUser(user);

        return ResponseEntity.ok("User is valid");
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

}
