package by.auto.artur.controllers;

import by.auto.artur.dto.UserDto;
import by.auto.artur.entity.User;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.mapper.AdvertisementMapper;
import by.auto.artur.mapper.UserMapper;
import by.auto.artur.service.AdvertisementService;
import by.auto.artur.service.UserService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/profile")
@AllArgsConstructor
public class UserProfileController {

    private final AdvertisementService advertisementService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AdvertisementMapper advertisementMapper;

    @GetMapping("/hello")
    public String hello (Principal principal){
        return "Hello " + principal.getName();
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUser(@Valid @RequestBody User user, @PathVariable("userId") long userId, Principal principal){
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getId() == userId || user.getRoles().stream().filter(r -> r.getName().
                equals("ADMIN")).findFirst().isPresent()) {

            userService.saveUser(user);
            return userMapper.userToDto(user);
        }

        throw new NoSuchContentException("Access is denied");
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser( @PathVariable("userId") long userId, Principal principal) {

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getId() == userId || userPrincipal.getRoles().stream().filter(r -> r.getName().
                equals("ADMIN")).findFirst().isPresent()) {

            userService.deleteUser(userId);
            return ResponseEntity.ok(String.format("Deletion of user '%s' was successful", userPrincipal.getUsername()));
        }

        throw new NoSuchContentException("Access is denied");
    }

}
