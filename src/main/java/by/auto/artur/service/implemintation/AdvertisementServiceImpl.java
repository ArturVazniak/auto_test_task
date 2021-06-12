package by.auto.artur.service.implemintation;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.repository.AdvertisementRepository;
import by.auto.artur.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * added check for exceptions and log (lombok)
 *
 *
 *@Author ArturVazniak
 */

@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository repository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<Advertisement> getAllAdvertisement() {

        List<Advertisement> advertisements = repository.findAll();
        log.info("IN getAllUsers : {} advertisements found", advertisements.size());
        return advertisements;
    }

    @Override
    public void saveAdvertisement(Advertisement advertisement) {

        repository.save(advertisement);
        log.info("IN saveAdvertisement - advertisement successfully save");
    }

    @Override
    public Advertisement getAdvertisement(long id) {

        Advertisement advertisement = repository.findById(id).orElse(null);

        if(advertisement == null) {
            log.warn("IN getAdvertisement - no advertisement found by id {} ", id);
            return null;
        }
        log.info("IN getAdvertisement advertisement: {} found by id: {}", advertisement, id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteAdvertisement(long id) {
        Advertisement advertisement = repository.findById(id).orElse(null);

        if(advertisement != null){
            repository.deleteById(id);
            log.info("IN deleteAdvertisement advertisement with id: {} successfully deleted", id);
        }
        log.warn("IN deleteAdvertisement advertisement with id: {} not found", id);
    }

    @Override
    public List<Advertisement> filterByYear(int year) {

        List<Advertisement> list = repository.findAdvertisementByYear(year);

        if(list.isEmpty()){
            log.warn("IN filterByYear advertisements with year: {} not found", year);
            return null;
        }
        log.info("IN filterByYear advertisements with year: {} successfully found", year);
        return list;
    }

    @Override
    public List<Advertisement> filterByCarModel(String model) {

        List<Advertisement> list = repository.findAdvertisementByCarModel(model);
        if(list.isEmpty()){
            log.warn("IN filterByCarModel advertisements with model of car: {} not found", model);
            return null;
        }
            log.info("IN filterByCarModel advertisements with model of car: {} successfully found", model);
            return list;

    }
}
