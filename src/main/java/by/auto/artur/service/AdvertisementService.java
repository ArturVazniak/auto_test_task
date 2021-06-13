package by.auto.artur.service;

import by.auto.artur.entity.Advertisement;

import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

public interface AdvertisementService {

     List<Advertisement> getAllAdvertisement(boolean isDeleted);

     void saveAdvertisement(Advertisement advertisement);

     Advertisement getAdvertisement(long id);

     void deleteAdvertisement(long id);

     List<Advertisement> filterByYear(int year);

     List<Advertisement> filterByCarModel(String model);

     List<Advertisement> findAdvertisementByPrice(int pageNo, int pageSize);

     List<Advertisement> findAllByCarModel(int pageNo, int pageSize);

     List<Advertisement> findAllByYear(int pageNo, int pageSize);

}
