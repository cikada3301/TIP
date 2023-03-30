package com.GraduateWork.UserMicroService.converter;

import com.GraduateWork.UserMicroService.dto.UserModificationDto;
import com.GraduateWork.UserMicroService.dto.UserRegistrationDto;
import com.GraduateWork.UserMicroService.model.User;

public interface UserConverter {
    User convertFromRegistrationDto(UserRegistrationDto userRegistrationDto);
    User convertFromModificationDto(UserModificationDto userModificationDto, User user);
}
