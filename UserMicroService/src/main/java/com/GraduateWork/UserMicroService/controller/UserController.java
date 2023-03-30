package com.GraduateWork.UserMicroService.controller;

import com.GraduateWork.UserMicroService.dto.UserAuthenticationDto;
import com.GraduateWork.UserMicroService.dto.UserModificationDto;
import com.GraduateWork.UserMicroService.dto.UserRegistrationDto;
import com.GraduateWork.UserMicroService.model.User;
import com.GraduateWork.UserMicroService.repository.UserRepository;
import com.GraduateWork.UserMicroService.security.JwtUtil;
import com.GraduateWork.UserMicroService.security.userDetails.JwtUser;
import com.GraduateWork.UserMicroService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUserName(@PathVariable String username) {
        return ResponseEntity.ok(userRepository.findByEmail(username));
    }

    @PostMapping("/authentication")
    public ResponseEntity<String> authenticate(@RequestBody UserAuthenticationDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        if (userDetails == null) {
            throw new BadCredentialsException("Not Valid email or password");
        }

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.register(userRegistrationDto);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegistrationDto.getEmail(), userRegistrationDto.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRegistrationDto.getEmail());

        if (userDetails == null) {
            throw new BadCredentialsException("Not Valid email or password");
        }

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PutMapping("/modification")
    public ResponseEntity<Void> modify(@RequestBody UserModificationDto userModificationDto, @AuthenticationPrincipal JwtUser jwtUser) {
        userService.edit(userModificationDto, jwtUser);
        return ResponseEntity.ok().build();
    }
}
