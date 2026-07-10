package com.project.fitness.service;

import com.project.fitness.dto.LoginRequest;
import com.project.fitness.dto.LoginResponse;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.reprository.UserReprository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReprository userReprository;
    private final PasswordEncoder passwordEncoder;
    public UserResponse register(RegisterRequest request) {
        UserRole role= request.getRole() !=null ? request.getRole()
                : UserRole.USER;
        User user=User.builder()
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .lastName(request.getLastName())
                .role(role).build();//builder is used for building the objcet
//        User user= new User(
//            null,
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName(),
//                Instant.parse("2026-04-12T16:14:02.236Z").atZone(ZoneOffset.UTC).toLocalDateTime(),
//                Instant.parse("2026-04-12T16:14:02.236Z").atZone(ZoneOffset.UTC).toLocalDateTime(),List.of(),
//                List.of()
//        );
        User saveduser=userReprository.save(user);
        return mapToResponse(saveduser);
    }

    public UserResponse mapToResponse(User saveduser) {
        UserResponse response=new UserResponse();
        response.setId(saveduser.getId());
        response.setEmail(saveduser.getEmail());
        response.setPassword(saveduser.getPassword());
        response.setFirstName(saveduser.getFirstName());
        response.setLastName(saveduser.getLastName());
        response.setCreatedAt(saveduser.getCreated_at());
        response.setUpdatedAt(saveduser.getUpdated_at());
        return response;

    }


    public User authenticated(LoginRequest loginRequest) {
        User user = userReprository.findByEmail(loginRequest.getEmail());
        if(user==null){
            throw new RuntimeException("Invalid Credentials") ;
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Credentials") ;
        }
        return user;
    }
}
