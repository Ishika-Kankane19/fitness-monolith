package com.project.fitness.security;

import com.project.fitness.model.User;
import com.project.fitness.reprository.UserReprository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.ListTokenSource;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserReprository userReprository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userReprository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User not found"+email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
