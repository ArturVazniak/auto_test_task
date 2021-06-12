package by.auto.artur.controller;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.AdvertisementService;
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
@RequestMapping("/api/v2")
public class HomeController {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    @Autowired
    public HomeController( UserService userService, AdvertisementService advertisementService) {

        this.advertisementService= advertisementService;
        this.userService = userService;
    }

    @GetMapping("/advertisements")
    public List<Advertisement> allAdvertisements(){

        return advertisementService.getAllAdvertisement();
    }

    @GetMapping("/advertisements/{id}")
    public Advertisement getAdvertisement(@PathVariable String id){

        long longId = Integer.parseInt(id);

        Advertisement advertisement = advertisementService.getAdvertisement(longId);
        if(advertisement == null){
            throw new NoSuchContentException("No advertisement with ID : "
                    + id + " in database");
        }
       return advertisementService.getAdvertisement(longId);
    }

    @PostMapping("/advertisements")
    public void addNewAdvertisement(@RequestBody Advertisement advertisement){

        advertisementService.saveAdvertisement(advertisement);
    }

    @PutMapping("/advertisements")
    public void updateAdvertisement(@RequestBody Advertisement advertisement){

        advertisementService.saveAdvertisement(advertisement);
    }

    @DeleteMapping("/advertisements/{id}")
    public void deleteAdvertisement(@PathVariable String id) {

        long longId = Integer.parseInt(id);
        advertisementService.deleteAdvertisement(longId);
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
    public void updateUser(@RequestBody User user){

        userService.saveUser(user);
    }

    @GetMapping("/filterYear/{year}")
    public List<Advertisement> getByYear(@PathVariable String year){

        return advertisementService.filterByYear(Integer.parseInt(year));
    }

    @GetMapping("/filterModel/{model}")
    public List<Advertisement> getByModel(@PathVariable String model){

        return advertisementService.filterByCarModel(model);
    }
}
