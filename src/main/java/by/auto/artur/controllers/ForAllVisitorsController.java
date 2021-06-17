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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@AllArgsConstructor
public class ForAllVisitorsController {

    private final AdvertisementService advertisementService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AdvertisementMapper advertisementMapper;

    @GetMapping("/advertisements/all/{isDeleted}")
    public ResponseEntity<List<AdvertisementDto>> allAdvertisements(@PathVariable boolean isDeleted){

        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                advertisementService.getAllAdvertisement(isDeleted)), HttpStatus.OK);
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
    public ResponseEntity<User> registerNewUserAccount(@Valid @RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.registerNewUserAccount(userDto), HttpStatus.CREATED);
    }

    //====================================== Pagination + Filters ======================================================

    @GetMapping("/advertisements/filter")
    public ResponseEntity<List<AdvertisementDto>> findAdvertisementSortByPrice(
                                               @RequestParam(defaultValue = "2400.33") double price,
                                               @RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false,defaultValue = "3") int size,
                                               @RequestParam(required = false,defaultValue = "id") String filterField,
                                               @RequestParam(required = false,defaultValue = "true") boolean sortField){
        Pageable paging= null;

        if(sortField == true){
             paging = PageRequest.of(page, size, Sort.by(filterField).descending());
        }else {
            paging = PageRequest.of(page, size, Sort.by(filterField).ascending());
        }

         return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                 advertisementService.findByPrice(price,paging)),HttpStatus.OK);
    }

    @GetMapping("/advertisements/filter/model")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisementSortCarModel(
                                               @RequestParam(defaultValue = "Mazda") String model,
                                               @RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false,defaultValue = "3") int size,
                                               @RequestParam(required = false,defaultValue = "id") String filterField,
                                               @RequestParam(required = false,defaultValue = "true") boolean sortField){
        Pageable paging = null;

        if(sortField == true) {
            paging = PageRequest.of(page, size, Sort.by(filterField).descending());
        }else {
            paging = PageRequest.of(page, size, Sort.by(filterField).ascending());
        }

        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                advertisementService.findByCarModel(model, paging)),HttpStatus.OK);
    }

    @GetMapping("/advertisements/filter/year")
    public ResponseEntity<List<AdvertisementDto>> findAdvertisementSortByYear(
                                              @RequestParam(defaultValue = "Mazda") int year,
                                              @RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false,defaultValue = "3") int size,
                                              @RequestParam(required = false,defaultValue = "id") String filterField,
                                              @RequestParam(required = false,defaultValue = "true") boolean sortField) {
        Pageable paging = null;

        if(sortField == true) {
             paging = PageRequest.of(page, size, Sort.by(filterField).descending());
        }else {
            paging = PageRequest.of(page, size, Sort.by(filterField).ascending());
        }

        return new ResponseEntity<>(advertisementMapper.advertisementListToDto(
                advertisementService.findByYear(year, paging)), HttpStatus.OK);
    }

    //========================================== Protected methods =====================================================

    @PreAuthorize("hasAuthority('ADMIN') or #authUser.id == #userId")
    @PostMapping("/advertisements")
    public ResponseEntity<String> addNewAdvertisement(@Valid @RequestBody Advertisement advertisement){
            advertisementService.saveAdvertisement(advertisement);
            return ResponseEntity.ok(String.format("Advertisement '%s' is valid", advertisement.getName()));
    }

    @PreAuthorize("hasAuthority('ADMIN') or #authUser.id == #userId")
    @PutMapping("/advertisements/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@Valid @RequestBody Advertisement advertisement,
                                                                @PathVariable("id") long id){

                advertisementService.saveAdvertisement(advertisement);
                return new ResponseEntity<>(advertisementMapper.advertisementToDto(advertisement), HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('ADMIN') or #authUser.id == #userId")
    @DeleteMapping("/advertisements/{id}")
    public ResponseEntity<String> deleteAdvertisement( @PathVariable("id") long id) {

        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.ok(String.format("Deletion of Advertisement '%s' was successful", id));
    }
}

