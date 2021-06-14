package by.auto.artur.dto;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@Data
public class AdvertisementDto {

    @Id
    private long id;
    private String name;
    private String text;
    private long author;
    private String carModel;
    private int year;
    private double price;
    private Date creationDate;
}
