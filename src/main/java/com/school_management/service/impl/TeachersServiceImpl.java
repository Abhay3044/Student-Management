package com.school_management.service.impl;

import com.school_management.entity.Teachers;
import com.school_management.repository.TeachersRepository;
import com.school_management.repository.UsersRepository;
import com.school_management.service.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TeachersServiceImpl implements TeachersService {

    private final TeachersRepository teachersRepository;

    private final UsersRepository usersRepository;

    @Override
    public List<Teachers> getAllTeachers() {
        return teachersRepository.findAll();
    }

    @Override
    public void addTecher(Teachers teachers) {
        teachersRepository.save(teachers);

        Optional<Object> teacher = usersRepository.findByUserName(teachers.getName()).map(teacher1 -> {
            teacher1.setRoles(Collections.singletonList("TEACHER"));
            return usersRepository.save(teacher1);
        });
    }
}
