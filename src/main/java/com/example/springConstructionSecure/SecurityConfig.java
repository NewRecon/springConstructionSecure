package com.example.springConstructionSecure;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    DataSource dataSource;
    @Bean
    JdbcUserDetailsManager setUser(DataSource dataSource){

//        UserDetails admin = User.withUsername("admin").password(encode().encode("admin")).roles(Role.ADMIN.name(),Role.USER.name()).build();
//        UserDetails user = User.withUsername("user").password(encode().encode("user")).roles(Role.USER.name()).build();

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encode());
        return authenticationProvider;

    }

    @Bean
    BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.formLogin();
//        http.httpBasic();
//        http.authorizeHttpRequests().requestMatchers("/startPage","/startPage1").permitAll();
//        http.authorizeHttpRequests().requestMatchers("/startPage2").authenticated();
//        http.authorizeHttpRequests().requestMatchers("/startPage3").denyAll();

        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(
                customizer->{
                    customizer.requestMatchers("/startPage","/startPage1").permitAll();
                    customizer.requestMatchers("/startPage2").authenticated();
                    customizer.requestMatchers("/startPage3").hasRole(Role.ADMIN.name());
                }
        );

        return http.build();
    }

}