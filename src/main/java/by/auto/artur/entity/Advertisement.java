package by.auto.artur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_advertisement")
    private String nameAdvertisement;

    @Column(name = "text")
    private String text;

    @Column(name = "author_id")
    private long author;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "year")
    private int year;



}
