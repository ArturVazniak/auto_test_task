package by.auto.artur.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

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
@Table(name = "users_auto")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 5, message = "\n" + "username must be at least 5 characters long")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "name")
    private String username;

    @Size(min = 8, message = "password must be at least 8 characters long")
    @Pattern(regexp = "[a-zA-Z0-9_\\-]+", message = "Should not contain only numbers")
    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @Email
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private List<Advertisement> list;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
