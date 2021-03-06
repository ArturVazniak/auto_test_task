package by.auto.artur.service.implementation;

import by.auto.artur.entity.Role;
import by.auto.artur.entity.User;
import by.auto.artur.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;


    public User findByUserName(String username){

        return userRepository.findUserByUsername(username);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
            return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));

        return userDetails;
    }
}
