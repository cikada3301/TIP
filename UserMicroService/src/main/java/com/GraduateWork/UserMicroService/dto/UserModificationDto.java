package com.GraduateWork.UserMicroService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModificationDto {
    private String gender;
    private String weight;
    private String height;
}
