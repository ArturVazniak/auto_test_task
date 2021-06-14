package by.auto.artur.service;

import by.auto.artur.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Date;
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

     List<Advertisement> findByYear(int year, Pageable pageable);

     List<Advertisement> findByCarModel(String model, Pageable pageable);

     List<Advertisement> findByPrice(double price, Pageable pageable);

}
