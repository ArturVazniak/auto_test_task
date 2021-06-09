package by.auto.artur.service;

import by.auto.artur.entity.Advertisement;
import by.auto.artur.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class AdvertisementServiceImpl implements AdvertisementService{

    private final AdvertisementRepository repository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {

        return repository.findAll();
    }

    @Override
    public void saveAdvertisement(Advertisement advertisement) {

        repository.save(advertisement);
    }

    @Override
    public Advertisement getAdvertisement(int id) {

        Optional<Advertisement> optional =  repository.findById(id);
        Advertisement advertisement = optional.get();

        return advertisement;
    }

    @Override
    public void deleteAdvertisement(int id) {

        repository.deleteById(id);
    }
}
