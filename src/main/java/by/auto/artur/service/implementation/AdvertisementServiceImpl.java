package by.auto.artur.service.implementation;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.repository.AdvertisementRepository;
import by.auto.artur.service.AdvertisementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 *
 * added check for exceptions and log (lombok)
 *
 *
 *@Author ArturVazniak
 */

@Service
@Slf4j
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository repository;
    private final EntityManager entityManager;

    @Override
    public List<Advertisement> getAllAdvertisement(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedAdvertisementFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Advertisement> advertisements = repository.findAll();
        session.disableFilter("deletedAdvertisementFilter");

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

        if (advertisement == null) {
            log.warn("IN getAdvertisement - no advertisement found by id {} ", id);
            return null;
        }
        log.info("IN getAdvertisement advertisement: {} found by id: {}", advertisement, id);
        return advertisement;
    }

    @Override
    public void deleteAdvertisement(long id) {
        Optional <Advertisement> advertisement = repository.findById(id);

        if (advertisement.isPresent()) {
            log.info("IN deleteAdvertisement advertisement with id: {} successfully deleted", id);
            repository.deleteById(id);
        }
        log.warn("IN deleteAdvertisement advertisement with id: {} not found", id);
    }

    @Override
    public List<Advertisement> findByYear(int year, Pageable pageable) {
        List<Advertisement> advertisements = repository.findByYear(year, pageable);

        if (!advertisements.isEmpty()) {
            log.info("IN findByYear advertisements : {} successfully found", advertisements.size());
            return advertisements;
        }
        log.warn("IN findByYear advertisements : {} not found", advertisements.size());
        return null;
    }

    @Override
    public List<Advertisement> findByCarModel(String model, Pageable pageable) {
        List<Advertisement> advertisements= repository.findByCarModel(model,pageable);

        if (!advertisements.isEmpty()) {
            log.info("IN findByCarModel advertisements : {} successfully found", advertisements.size());
            return advertisements;
        }
        log.warn("IN findByCarModel advertisements : {} not found", advertisements.size());
        return null;
    }

    @Override
    public List<Advertisement> findByPrice(double price, Pageable pageable) {
        List<Advertisement> advertisements = repository.findByPrice(price, pageable);

        if (!advertisements.isEmpty()) {
            log.info("IN findByPrice advertisements : {} successfully found", advertisements.size());
            return advertisements;
        }
        log.warn("IN findByPrice advertisements : {} not found", advertisements.size());
        return null;
    }
}

