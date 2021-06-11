package by.auto.artur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 *
 * Uni-directional associations
 * Relationships between tables : users (OneToMany) - advertisements | user (OneToOne) - roles
 *
 *@Author ArturVazniak
 */


@Entity
@Data
@NoArgsConstructor
@Table(name = "users_auto")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private List<Advertisement> list;

    @OneToOne
    @JoinColumn(name = "role")
    private Role roles;

}
