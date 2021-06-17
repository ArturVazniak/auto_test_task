package by.auto.artur.entity;

import lombok.Data;

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
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String name;
}
