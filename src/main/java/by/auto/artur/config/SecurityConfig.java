package by.auto.artur.config;

import by.auto.artur.service.implementation.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@EnableWebSecurity//(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityService userSecurityService;

    @Autowired
    public SecurityConfig(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    // Standard form authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user/**").authenticated()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/guest/advertisements/role/true").hasRole("ADMIN")
                .antMatchers("/api/guest/advertisements/role/false").permitAll()
                .antMatchers("/api/guest/**").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/api/user/hello")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

       return new BCryptPasswordEncoder();
    }

    // daoAuthenticationProvider finds out if the user exists in our database and if so,
    // transfers it to SecurityContext
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // password hashing
        authenticationProvider.setUserDetailsService(userSecurityService); // checking the availability of users

        return authenticationProvider;
    }
}
