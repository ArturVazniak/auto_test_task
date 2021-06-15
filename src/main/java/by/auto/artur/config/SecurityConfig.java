package by.auto.artur.config;

import by.auto.artur.service.implementation.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@EnableWebSecurity //(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityService userSecurityService;
    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(UserSecurityService userSecurityService, DataSource dataSource) {
        this.userSecurityService = userSecurityService;
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT  name, password, "
                        + "enabled FROM users_auto WHERE name = ?")
                .authoritiesByUsernameQuery("SELECT users_auto.name, roles.role_name  FROM users_auto "
                        + "INNER JOIN users_roles ON users_auto.id = users_roles.user_id "
                        + "INNER JOIN roles ON users_roles.role_id = roles.id WHERE name = ?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/admin/**").hasAuthority("ADMIN")
                .antMatchers("/api/guest/advertisements/all/true").hasAuthority("ADMIN") //  only admins see deleted advertisements
                .antMatchers("/api/user/**").authenticated()
                .antMatchers("/api/guest/advertisements/all/false").permitAll()
                .antMatchers("/api/guest/**").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/api/user/hello")
                .and()
                .logout().logoutSuccessUrl("/login");
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

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
