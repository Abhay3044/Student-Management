package com.school_management.service;

import com.school_management.entity.Students;

import java.util.List;

public interface StudentsService {

    List<Students> getAllStudents();

    void addStudent(Students students, long userId);

    Students getByName(String name);
}
