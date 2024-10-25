package com.school_management.service.impl;

import com.school_management.entity.Students;
import com.school_management.entity.Users;
import com.school_management.repository.UsersRepository;
import com.school_management.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class UsersServiceImpl implements UsersService {

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addNewUser(Users user) {
        try {
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            user.setRoles(List.of("USER"));
            usersRepository.save(user);
            log.info("User added successfully");
        } catch (Exception e) {
            log.error("error while saving the user");
        }
    }

}
