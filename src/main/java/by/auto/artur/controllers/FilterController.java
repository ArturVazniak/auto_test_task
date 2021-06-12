package by.auto.artur.controllers;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.AdvertisementService;
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
@RequestMapping("/api/filter")
public class FilterController {

    private final AdvertisementService advertisementService;

    @Autowired
    public FilterController(AdvertisementService advertisementService) {

        this.advertisementService= advertisementService;
    }

    @GetMapping("/Year/{year}")
    public List<Advertisement> getByYear(@PathVariable String year){

        List<Advertisement> list = advertisementService.filterByYear(Integer.parseInt(year));

        if(list == null){ // comparison with null because the method returns null if nothing was found
            throw new NoSuchContentException("No advertisement with year : "
                    + year + " in database");
        }
        return list;
    }

    @GetMapping("/Model/{model}")
    public List<Advertisement> getByModel(@PathVariable String model){

        List<Advertisement> list = advertisementService.filterByCarModel(model);

        if(list == null){
            throw new NoSuchContentException("No advertisement with model : "
                    + model + " in database");
        }
        return list;
    }

}
