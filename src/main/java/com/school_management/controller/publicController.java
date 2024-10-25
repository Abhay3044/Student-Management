package com.school_management.controller;


import com.school_management.config.CustomizedUserDetailService;
import com.school_management.entity.Users;
import com.school_management.service.UsersService;
import com.school_management.utility.JwtUtils;
import com.school_management.utility.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/public")
@Tag(name = "Public APIs", description = "SignUp Login and App heath check")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class publicController {

    private final UsersService usersService;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final CustomizedUserDetailService customizedUserDetailService;

    @GetMapping("/home")
    @Operation(summary = "checking the application health")
    public String heathCheck() {
        return "App is working fine. you are in public section";
    }

    @PostMapping("/registration")
    @Operation(summary = "First you have to register your self then admin assign you your role")
    public String signUp(@RequestBody Users user) {
        if(ObjectUtils.isEmpty(user.getRoles())) {
            user.setRoles(Collections.singletonList("VISITOR"));
        }
        usersService.addNewUser(user);
        return "Registration done successfully";
    }

    @PostMapping("/login")
    @Operation(summary = "To access the app please signIn")
    public String authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));
        if(authentication.isAuthenticated()) {
            return jwtUtils.generateTokenFromUsername(customizedUserDetailService.loadUserByUsername(loginRequest.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

}
