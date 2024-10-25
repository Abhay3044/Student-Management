package com.school_management.service.impl;

import com.school_management.entity.Students;
import com.school_management.repository.StudentsRepository;
import com.school_management.repository.UsersRepository;
import com.school_management.service.StudentsService;
import com.school_management.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public void addStudent(Students students, long userId) {
        studentsRepository.save(students);
    }

    @Override
    public Students getByName(String name) {
        return studentsRepository.findByName(name);
    }
}
