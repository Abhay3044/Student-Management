package com.school_management.service;

import com.school_management.entity.Students;
import com.school_management.entity.Teachers;

import java.util.List;

public interface TeachersService {
    List<Teachers> getAllTeachers();

    void addTecher(Teachers teachers);
}
