package by.auto.artur.controllers;

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
@RequestMapping("/api/guest")
public class ForAllVisitorsController {

    private final AdvertisementService advertisementService;
    private final UserService userService;


    @Autowired
    public ForAllVisitorsController(AdvertisementService advertisementService, UserService userService) {
        this.userService = userService;
        this.advertisementService = advertisementService;
    }


    @GetMapping("/advertisements/roles/{role}")
    public List<Advertisement> allAdvertisements(@PathVariable boolean role){

        return advertisementService.getAllAdvertisement(role);
    }

    @GetMapping("/advertisements/{id}")
    public Advertisement getAdvertisement(@PathVariable String id){

        long longId = Integer.parseInt(id);

        Advertisement advertisement = advertisementService.getAdvertisement(longId);
        if(advertisement == null){
            throw new NoSuchContentException("No advertisement with ID : "
                    + id + " in database");
        }
        return advertisement;
    }

    @GetMapping("/advertisements/price/{pageNo}/{pageSize}")
    public List<Advertisement> getAllPrice(@PathVariable int pageNo,
                                           @PathVariable int pageSize) {

        List<Advertisement> list= advertisementService.findAllByYear(pageNo,pageSize);

        if(list == null){ // comparison with null because the method returns null if nothing was found
            throw new NoSuchContentException("The page "+ pageNo+ " does not exist");
        }
        return list;
    }

    @GetMapping("/advertisements/model/{pageNo}/{pageSize}")
    public List<Advertisement> findAllByCarModel(@PathVariable int pageNo,
                                                 @PathVariable int pageSize) {

        List<Advertisement> list = advertisementService.findAllByYear(pageNo, pageSize);

        if (list == null) {
            throw new NoSuchContentException("The page " + pageNo + " does not exist");
        }
        return list;
    }

    @GetMapping("/advertisements/year/{pageNo}/{pageSize}")
    public List<Advertisement> findAllByYear(@PathVariable int pageNo,
                                             @PathVariable int pageSize) {

        List<Advertisement> list= advertisementService.findAllByYear(pageNo,pageSize);

        if(list == null){
            throw new NoSuchContentException("The page "+ pageNo +" does not exist");
        }
        return list;
    }

    @GetMapping("/filter/Year/{year}")
    public List<Advertisement> getByYear(@PathVariable String year){

        List<Advertisement> list = advertisementService.filterByYear(Integer.parseInt(year));

        if(list == null){ // comparison with null because the method returns null if nothing was found
            throw new NoSuchContentException("No advertisement with year : "
                    + year + " in database");
        }
        return list;
    }

    @GetMapping("/filter/Model/{model}")
    public List<Advertisement> getByModel(@PathVariable String model){

        List<Advertisement> list = advertisementService.filterByCarModel(model);

        if(list == null){
            throw new NoSuchContentException("No advertisement with model : "
                    + model + " in database");
        }
        return list;
    }

    @PostMapping("/users")
    public ResponseEntity<String> registrationUser(@Valid @RequestBody User user){

        userService.saveUser(user);

        return ResponseEntity.ok("User is valid");
    }

}
