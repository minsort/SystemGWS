package com.example.gws.config;

import com.example.gws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //определяет, какие URL-адреса должны быть защищены, а какие нет
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()

                // не требовать какой-либо аутентификации
                .antMatchers("/r").permitAll()

                // требовать аутентификации
                .antMatchers("/a").hasRole("ADMIN")
                .antMatchers("/c").hasRole("USER")

                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }




      /*  httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()



                //.antMatchers("/news").hasRole("USER")

                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/r").not().fullyAuthenticated()

                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/a/**").hasRole("ADMIN")


                //Доступ разрешен всем пользователей
                .antMatchers("/", "/registration/**").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()

                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")

                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/r")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/r");*/


   /* @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }*/
}