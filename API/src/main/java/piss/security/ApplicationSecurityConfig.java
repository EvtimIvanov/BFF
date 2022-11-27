package piss.security;

import piss.UserRepository;
import piss.service.DetailsService;

import piss.service.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ApplicationSecurityConfig {

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new DetailsService(userRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JWTFilter jwtFilter) throws Exception {

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http
                .authorizeRequests()
                .antMatchers("/", "/index", "/login","/register").permitAll()
                .anyRequest().authenticated()
                .and()
                    .cors()
                .and()
                    .csrf().disable()
                .build();
//                .csrf().disable()
//                .cors()
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/", "/index", "/login").permitAll()
//                    .antMatchers("/register").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .httpBasic(withDefaults())
//                .build();


//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
////                .defaultSuccessUrl("/", true)
////                .and()
////                .authorizeRequests()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher(
//                        "/logout", "POST"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login")
//                .and()
//                .build();


    }



}
