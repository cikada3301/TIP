package com.GraduateWork.UserMicroService.converter;

import com.GraduateWork.UserMicroService.dto.UserModificationDto;
import com.GraduateWork.UserMicroService.dto.UserRegistrationDto;
import com.GraduateWork.UserMicroService.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User convertFromRegistrationDto(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .email(userRegistrationDto.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRegistrationDto.getPassword()))
                .build();
    }

    @Override
    public User convertFromModificationDto(UserModificationDto userModificationDto, User user) {
        user.setGender(userModificationDto.getGender());
        user.setWeight(userModificationDto.getWeight());
        user.setHeight(userModificationDto.getHeight());
        return user;
    }
}
