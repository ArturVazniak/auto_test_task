package by.auto.artur.controllers;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.AdvertisementService;
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
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisements/role/{role}")
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
}
