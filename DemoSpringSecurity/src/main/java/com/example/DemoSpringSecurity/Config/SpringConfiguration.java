package com.example.DemoSpringSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private jwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); //token disabled first what we created that
        //sagla authenticate kela kon pn logic nhi karu shakat with pass and username
        http.authorizeHttpRequests(req -> req.
                requestMatchers("users","login").permitAll()
                .anyRequest().authenticated());
       // http.formLogin(Customizer.withDefaults()); // login form ui disnar this is for browser
        http.httpBasic(Customizer.withDefaults()); //postman api sathi tithlya access sathi

        //EVERY TIME WE GET NEW SESSION
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

   // connect database and autnetication obj
    public AuthenticationProvider authenticationProvider(){
        //it is a class for database
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //no password encoder use dafault one
        provider.setPasswordEncoder( new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // No encoding, plain text passwords
    }

    //1st step of jwt after adding dependency create authenticationmanager obj and hold it it talk with autnetication provider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }





    //create users
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//                .password("{noop}user1")
//                .roles("USER1")
//                .build();
//
//        UserDetails user2 = User.withUsername("user2")
//                .password("{noop}user2")
//                .roles("USER2")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}
