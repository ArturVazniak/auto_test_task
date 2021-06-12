package by.auto.artur.controllers;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.exceptions.NoSuchContentException;
import by.auto.artur.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created a pagination with three sorting methods: price, year, car model
 * Methods getAllPrice and findAllByYear are ascending sort
 * Method findAllByCarModel is descending sort
 *
 *@Author ArturVazniak
 */

@RestController
@RequestMapping("/api/pagination")
public class PaginationController {

    private final AdvertisementService advertisementService;

    @Autowired
    public PaginationController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
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

        List<Advertisement> list= advertisementService.findAllByYear(pageNo,pageSize);

        if(list == null){
            throw new NoSuchContentException("The page "+ pageNo +" does not exist");
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
}
