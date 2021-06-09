package by.auto.artur.controller;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final AdvertisementService advertisementService;

    @Autowired
    public HomeController(AdvertisementService advertisementService) {

        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisements")
    public List<Advertisement> allAdvertisements(){

        return advertisementService.getAllAdvertisements();
    }

    @GetMapping("/advertisements/{id}")
    public Advertisement getAdvertisement(@PathVariable int id){

       return advertisementService.getAdvertisement(id);
    }

    @PostMapping("/advertisements")
    public void addNewAdvertisement(@RequestBody Advertisement advertisement){

        advertisementService.saveAdvertisement(advertisement);
    }

    @PutMapping("/advertisements")
    public String updateAdvertisement(@RequestBody Advertisement advertisement){

        advertisementService.saveAdvertisement(advertisement);

        return "redirect:/advertisements";
    }

    @DeleteMapping("/advertisements/{id}")
    public String deleteAdvertisement(@PathVariable int id){

        advertisementService.deleteAdvertisement(id);

        return "redirect:/advertisements";
    }
}
