package by.auto.artur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Size(min = 5, message = "username must be 5 symbols")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "name")
    private String username;

    @Size(min = 8, message = "password must be 8 symbols")
    @Pattern(regexp = "[a-zA-Z0-9_\\-]+", message = "Should not contain only numbers")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "password")
    private String password;

    @Email
    @NotBlank(message = "Username is mandatory")
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private List<Advertisement> list;

    @OneToOne
    @JoinColumn(name = "role")
    private Role roles;

}
