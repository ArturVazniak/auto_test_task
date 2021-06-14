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
import org.hibernate.annotations.ParamDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PostMapping("/users")
    public ResponseEntity<User> registrationUser(@Valid @RequestBody UserDto userDto){
       User user = userService.saveUser(userMapper.DtoFromUser(userDto));

        return new ResponseEntity<>(userService.saveUser(userMapper.DtoFromUser(userDto)),HttpStatus.CREATED);
    }

    @GetMapping("/advertisements/filter")
    public ResponseEntity<List<AdvertisementDto>> findAdvertisementSortByPrice(@RequestParam(defaultValue = "2400.33") double price,
                                                                               @RequestParam(required = false, defaultValue = "0") int page,
                                                                               @RequestParam(required = false,defaultValue = "3") int size,
                                                                               @RequestParam(required = false,defaultValue = "id") String field,
                                                                               @RequestParam(required = false,defaultValue = "true") boolean sort){
        Pageable paging= null;

        if(sort == true){
             paging = PageRequest.of(page, size, Sort.by(field).descending());
        }else {
            paging = PageRequest.of(page, size, Sort.by(field).ascending());
        }

         return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                 advertisementService.findByPrice(price,paging)),HttpStatus.OK);
    }

    @GetMapping("/advertisements/filter/model")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisementSortCarModel(@RequestParam(defaultValue = "Mazda") String model,
                                                                           @RequestParam(required = false, defaultValue = "0") int page,
                                                                           @RequestParam(required = false,defaultValue = "3") int size,
                                                                           @RequestParam(required = false,defaultValue = "id") String field,
                                                                           @RequestParam(required = false,defaultValue = "true") boolean sort){
        Pageable paging = null;

        if(sort == true) {
            paging = PageRequest.of(page, size, Sort.by(field).descending());
        }else {
            paging = PageRequest.of(page, size, Sort.by(field).ascending());
        }

        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                advertisementService.findByCarModel(model, paging)),HttpStatus.OK);
    }

    @GetMapping("/advertisements/filter/year")
    public ResponseEntity<List<AdvertisementDto>> findAdvertisementSortByYear(@RequestParam(defaultValue = "Mazda") int year,
                                                                          @RequestParam(required = false, defaultValue = "0") int page,
                                                                          @RequestParam(required = false,defaultValue = "3") int size,
                                                                          @RequestParam(required = false,defaultValue = "id") String field,
                                                                          @RequestParam(required = false,defaultValue = "true") boolean sort) {
        Pageable paging = null;

        if(sort == true) {
             paging = PageRequest.of(page, size, Sort.by(field).descending());
        }else {
            paging = PageRequest.of(page, size, Sort.by(field).ascending());
        }

        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                advertisementService.findByYear(year, paging)), HttpStatus.OK);
    }

}

