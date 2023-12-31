package com.example.springsecdemo.config;

import com.example.springsecdemo.filter.MySecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfig {

//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("amit").password(passwordEncoder().encode("dummy")).roles("read").build();
//        userDetailsManager.createUser(user);
//        return userDetailsManager;
//    }

//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/test/hello").authenticated().anyRequest().denyAll())
                .addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class)
                .build();
    }
}
