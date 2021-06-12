package by.auto.artur.repository;

import by.auto.artur.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 *
 * search filters for advertisements
 *
 *@Author ArturVazniak
 */

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findAdvertisementByYear(int year);
    List<Advertisement> findAdvertisementByCarModel(String model);


}
