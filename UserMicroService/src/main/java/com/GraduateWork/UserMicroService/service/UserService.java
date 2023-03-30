package com.GraduateWork.UserMicroService.service;

import com.GraduateWork.UserMicroService.dto.UserModificationDto;
import com.GraduateWork.UserMicroService.dto.UserRegistrationDto;
import com.GraduateWork.UserMicroService.security.userDetails.JwtUser;

public interface UserService {
    void register(UserRegistrationDto userRegistrationDto);
    void edit(UserModificationDto userModificationDto, JwtUser user);
}
