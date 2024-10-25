package com.school_management.config;

import com.school_management.entity.Users;
import com.school_management.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomizedUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository dbUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = dbUsersRepository.findByUserName(username);
        if(user.isPresent()) {
            Users userFromDb = user.get();
            return User.builder()
                    .username(userFromDb.getUserName())
                    .password(userFromDb.getPassWord())
                    .roles(String.valueOf(userFromDb.getRoles()))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
