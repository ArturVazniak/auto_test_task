package by.auto.artur.service;

import by.auto.artur.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    public List<Advertisement> getAllAdvertisements();

    public void saveAdvertisement(Advertisement advertisement);

    public  Advertisement getAdvertisement(int id);

    public void deleteAdvertisement(int id);
}
