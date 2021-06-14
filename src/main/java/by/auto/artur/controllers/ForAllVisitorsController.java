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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final UserMapper userMapper;
    private final AdvertisementMapper advertisementMapper;

    @Autowired
    public ForAllVisitorsController(AdvertisementService advertisementService,
                                    UserService userService,
                                    UserMapper userMapper,
                                    AdvertisementMapper advertisementMapper) {

        this.userService = userService;
        this.advertisementService = advertisementService;
        this.userMapper = userMapper;
        this.advertisementMapper = advertisementMapper;
    }

    @GetMapping("/advertisements/all/{isDeleted}")
    public ResponseEntity<List<AdvertisementDto>> allAdvertisements(@PathVariable boolean isDeleted){

        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                advertisementService.getAllAdvertisement(isDeleted)), HttpStatus.CREATED);
    }

    @GetMapping("/advertisements/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable(name= "id") long id){
        Advertisement advertisement = advertisementService.getAdvertisement(id);

        if(advertisement == null){
            throw new NoSuchContentException("No advertisement with ID : "
                    + id + " in database");
        }
        return new ResponseEntity<> (advertisementMapper.advertisementToDto(advertisement),HttpStatus.OK);
    }

    //------------------------------- Pagination ---------------------------------------

    @GetMapping("/advertisements/price/{pageNo}/{pageSize}")
    public ResponseEntity<List<AdvertisementDto>> getAllPrice(@PathVariable int pageNo,
                                           @PathVariable int pageSize) {

        List<Advertisement> list= advertisementService.findAllByYear(pageNo,pageSize);

        if(list == null){ // comparison with null because the method returns null if nothing was found
            throw new NoSuchContentException("The page "+ pageNo+ " does not exist");
        }
        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(list),HttpStatus.OK);
    }

    @GetMapping("/advertisements/model/{pageNo}/{pageSize}")
    public ResponseEntity<List<AdvertisementDto>> findAllByCarModel(@PathVariable int pageNo,
                                                 @PathVariable int pageSize) {

        List<Advertisement> list = advertisementService.findAllByYear(pageNo, pageSize);

        if (list == null) {
            throw new NoSuchContentException("The page " + pageNo + " does not exist");
        }
        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(list),HttpStatus.OK);
    }

    @GetMapping("/advertisements/year/{pageNo}/{pageSize}")
    public ResponseEntity<List<AdvertisementDto>> findAllByYear(@PathVariable int pageNo,
                                             @PathVariable int pageSize) {

        List<Advertisement> list= advertisementService.findAllByYear(pageNo,pageSize);

        if(list == null){
            throw new NoSuchContentException("The page "+ pageNo +" does not exist");
        }
        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(list),HttpStatus.OK);
    }

    //------------------------------- Filters ----------------------------------------------

    @GetMapping("/filter/Year/{year}")
    public ResponseEntity<List<AdvertisementDto>> getByYear(@PathVariable int year){

        List<Advertisement> list = advertisementService.filterByYear(year);

        if(list == null){ // comparison with null because the method returns null if nothing was found
            throw new NoSuchContentException("No advertisement with year : "
                    + year + " in database");
        }
        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(list),HttpStatus.OK);
    }

    @GetMapping("/filter/Model/{model}")
    public ResponseEntity<List<AdvertisementDto>> getByModel(@PathVariable String model){

        List<Advertisement> list = advertisementService.filterByCarModel(model);

        if(list == null){
            throw new NoSuchContentException("No advertisement with model : "
                    + model + " in database");
        }
        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(list),HttpStatus.OK);
    }
    //------------------------------------------------------------------------------------------

    @PostMapping("/users")
    public ResponseEntity<User> registrationUser(@Valid @RequestBody UserDto userDto){
       User user = userService.saveUser(userMapper.DtoFromUser(userDto));

        return new ResponseEntity<>(userService.saveUser(userMapper.DtoFromUser(userDto)),HttpStatus.CREATED);
    }

}
