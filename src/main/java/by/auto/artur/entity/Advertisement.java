package by.auto.artur.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Date;

/**
 *
 * Create a validation
 * Created an "soft delete"
 * Create a filter "deletedAdvertisementFilter". Now deleted advertisements are visible only to admins
 * Added Date
 *
 *@Author ArturVazniak
 */

@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE advertisements SET deleted = true WHERE id=?")
@FilterDef(name = "deletedAdvertisementFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedAdvertisementFilter", condition = "deleted =:isDeleted")
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 5, message = "name of advertisement must be at least 5 characters long")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "name_advertisement")
    private String name;

    @Column(name = "text")
    private String text;


    @Column(name = "author_id")
    private long author;

    @NotBlank(message = "Model is mandatory")
    @Column(name = "car_model")
    private String carModel;

    @Min(value = 4, message = "Year should be only 4 numbers")
    @NotNull(message = "Year should be 4 numbers")
    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date creationDate;

    @Column(name = "deleted")
    private boolean deleted;

}
