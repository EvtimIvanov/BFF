package com.paysafe.service;

import com.paysafe.UserRepository;
import com.paysafe.entities.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> user = this.userRepository.findByEmail(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("There is no user with email:" + username);
        }

        return this.toDetails(user.get());

    }

    private UserDetails toDetails(Users user) {
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(String.valueOf(user.getUserRole()))
                .build();
    }
}
