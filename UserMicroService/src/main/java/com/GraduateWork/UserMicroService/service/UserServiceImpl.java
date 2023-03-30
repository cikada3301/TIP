package com.GraduateWork.UserMicroService.service;

import com.GraduateWork.UserMicroService.converter.UserConverter;
import com.GraduateWork.UserMicroService.dto.UserModificationDto;
import com.GraduateWork.UserMicroService.dto.UserRegistrationDto;
import com.GraduateWork.UserMicroService.model.User;
import com.GraduateWork.UserMicroService.repository.UserRepository;
import com.GraduateWork.UserMicroService.security.userDetails.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final WebClient webClient;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new JwtUser(user);
    }

    @Override
    @Transactional
    public void register(UserRegistrationDto userRegistrationDto) {
        User user = userConverter.convertFromRegistrationDto(userRegistrationDto);
        System.out.println(user);
        userRepository.save(user);

        webClient.get()
                .uri("http://localhost:8082/basket/" + user.getEmail())
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe();

    }

    @Override
    @Transactional
    public void edit(UserModificationDto userModificationDto, JwtUser jwtUser) {
        User user = userRepository.findByEmail(jwtUser.getUsername());
        userRepository.save(userConverter.convertFromModificationDto(userModificationDto, user));
    }
}
