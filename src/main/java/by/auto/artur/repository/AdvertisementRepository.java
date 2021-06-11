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

    public List<Advertisement> findAdvertisementByYear(int year);
    public List<Advertisement> findAdvertisementByCarModel(String model);
}
