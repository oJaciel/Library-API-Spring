package com.github.ojaciel.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(configurer -> {
                    configurer.loginPage("/login").permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll(); //Qualquer um pode acessar login, mesmo sem
                    // estar autenticado
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();


//                    authorize.requestMatchers(HttpMethod.POST, "/autores/**").hasRole("ADMIN"); //Somente admin pode acessar
//                    authorize.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("ADMIN"); //Somente admin pode
//                    // acessar
//                    authorize.requestMatchers(HttpMethod.PUT, "/autores/**").hasRole("ADMIN"); //Somente admin
//                    // pode acessar
//                    authorize.requestMatchers(HttpMethod.GET, "/autores/**").hasAnyRole("USER", "ADMIN"); //Admin e user pode acessar


                    authorize.requestMatchers("/autores/**").hasRole("ADMIN"); //Admin e user pode acessar
                    authorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN"); //Admin e user pode acessar
                    authorize.anyRequest().authenticated(); //Qualquer outra URL, que não seja mapeada, o usuário só
                    // precisa estar autenticado
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user1 = User.builder()
                .username("usuario")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder.encode("321"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

}
