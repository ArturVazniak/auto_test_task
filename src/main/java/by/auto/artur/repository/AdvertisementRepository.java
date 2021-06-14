package by.auto.artur.repository;

import by.auto.artur.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * search filters for advertisements
 *
 *@Author ArturVazniak
 */

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByYear(int year, Pageable pageable);
    List<Advertisement> findByCarModel(String model, Pageable pageable);
    List<Advertisement> findByPrice(double price, Pageable pageable);





}
