package com.GraduateWork.UserMicroService.repository;

import com.GraduateWork.UserMicroService.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
