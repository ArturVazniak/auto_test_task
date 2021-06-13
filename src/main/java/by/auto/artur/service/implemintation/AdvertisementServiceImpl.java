package by.auto.artur.service.implemintation;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.repository.AdvertisementRepository;
import by.auto.artur.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
    private final EntityManager entityManager;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository repository, EntityManager entityManager) {

        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Advertisement> getAllAdvertisement(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedAdvertisementFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Advertisement> advertisements =  repository.findAll();
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
        Advertisement advertisement = repository.findById(id).orElse(null);

        if (advertisement != null) {
            log.info("IN deleteAdvertisement advertisement with id: {} successfully deleted", id);
            repository.deleteById(id);
        }
        log.warn("IN deleteAdvertisement advertisement with id: {} not found", id);
    }


    @Override
    public List<Advertisement> filterByYear(int year) {

        List<Advertisement> list = repository.findAdvertisementByYear(year);

        if (list.isEmpty()) {
            log.warn("IN filterByYear advertisements with year: {} not found", year);
            return null;
        }
        log.info("IN filterByYear advertisements with year: {} successfully found", year);
        return list;
    }


    @Override
    public List<Advertisement> filterByCarModel(String model) {

        List<Advertisement> list = repository.findAdvertisementByCarModel(model);
        if (list.isEmpty()) {
            log.warn("IN filterByCarModel advertisements with model of car: {} not found", model);
            return null;
        }
        log.info("IN filterByCarModel advertisements with model of car: {} successfully found", model);
        return list;

    }

    @Override
    public List<Advertisement> findAdvertisementByPrice(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("price").descending());
        Page<Advertisement> pagedResult = repository.findAll(paging);

        if(pagedResult.isEmpty()){
            log.warn("IN findAdvertisementByPrice the page : {} does not exist", pageNo);
            return null;
        }
        log.warn("IN findAdvertisementByPrice the page : {} exist", pageNo);
        return pagedResult.toList();
    }

    @Override
    public List<Advertisement> findAllByCarModel(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("carModel").ascending());
        Page<Advertisement> pagedResult = repository.findAll(paging);

        if(pagedResult.isEmpty()){
            log.warn("IN findAllByCarModel the page : {} does not exist", pageNo);
            return null;
        }
        log.warn("IN findAllByCarModel the page : {} exist", pageNo);
        return pagedResult.toList();
    }

    @Override
    public List<Advertisement> findAllByYear(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("year").descending());
        Page<Advertisement> pagedResult = repository.findAll(paging);
        if(pagedResult.isEmpty()){
            log.warn("IN findAllByYear the page : {} does not exist", pageNo);
            return null;
        }
        log.warn("IN findAllByYear the page : {} exist", pageNo);
        return pagedResult.toList();
    }
}