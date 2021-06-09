package by.auto.artur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_advertisement")
    private String nameAdvertisement;

    @Column(name = "text")
    private String text;

    @Column(name = "roleid")
    private int roles;

    @Column(name= "author")
    private String author;

}
