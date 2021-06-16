package by.auto.artur.controllers;

import by.auto.artur.dto.AdvertisementDto;
import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.Advertisement;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.mapper.AdvertisementMapper;
import by.auto.artur.mapper.UserMapper;
import by.auto.artur.service.AdvertisementService;
import by.auto.artur.service.UserService;
import by.auto.artur.service.implementation.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserMapper userMapper;
    private final AdvertisementMapper advertisementMapper;

    @Autowired
    public ForAuthenticatedUsersController(AdvertisementService advertisementService,
                                           UserService userService,
                                           UserSecurityService userSecurityService,
                                           UserMapper userMapper,
                                           AdvertisementMapper advertisementMapper) {

        this.advertisementService = advertisementService;
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.userMapper = userMapper;
        this.advertisementMapper = advertisementMapper;
    }

    @GetMapping("/hello")
    public String hello (Principal principal){
        return "Hello " + principal.getName();
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.userId == #id")
    @PostMapping("/advertisements")
    public ResponseEntity<String> addNewAdvertisement(@Valid @RequestBody Advertisement advertisement){
        advertisementService.saveAdvertisement(advertisement);

        return ResponseEntity.ok(String.format("Advertisement '%s' is valid", advertisement.getName()));
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.userId == #id")
    @PutMapping("/advertisements")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@Valid @RequestBody Advertisement advertisement){
        advertisementService.saveAdvertisement(advertisement);

        return new ResponseEntity<>(advertisementMapper.advertisementToDto(advertisement), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.userId == #id")
    @DeleteMapping("/advertisements/{id}")
    public ResponseEntity<String> deleteAdvertisement(@PathVariable long id) {
        advertisementService.deleteAdvertisement(id);

        return ResponseEntity.ok(String.format("Deletion of Advertisement '%s' was successful", id));
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.userId == #id")
    @PutMapping("/users")
    public UserDto updateUser(@Valid @RequestBody User user){
        userService.saveUser(user);

        return userMapper.userToDto(user);
    }

    @PreAuthorize("hasAuthority('ADMIN') or principal.userId == #id")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        User user = userService.getUser(id);

        if(user == null){
            throw new NoSuchContentException("NO user with ID : " + id + " in database");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok(String.format("Deletion of user '%s' was successful", user.getUsername()));
    }

}
