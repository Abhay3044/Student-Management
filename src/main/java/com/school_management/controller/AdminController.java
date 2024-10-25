package com.school_management.controller;

import com.school_management.entity.Admin;
import com.school_management.entity.Students;
import com.school_management.entity.Teachers;
import com.school_management.service.AdminService;
import com.school_management.service.StudentsService;
import com.school_management.service.TeachersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs", description = "Mange the users ie : Students and teachers")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class AdminController {

    private final StudentsService studentsService;

    private final TeachersService teachersService;

    private final AdminService adminService;

    @GetMapping("/home")
    @Operation(summary = "Checking the application health")
    public String heathCheck() {
        return "App is working fine. you are in admin section";
    }

    @GetMapping("/allStudents")
    @Operation(summary = "Get List of all Students")
    public List<Students> getListOfAllStudents (){
        return studentsService.getAllStudents();
    }

    @GetMapping("/allTeachers")
    @Operation(summary = "Get List of all Teachers")
    public List<Teachers> getListOfAllTeachers (){
        return teachersService.getAllTeachers();
    }

    @PostMapping("/addStudent")
    @Operation(summary = "Add a Student")
    public String addStudents(@RequestBody Students students, @RequestParam long userId) {
        studentsService.addStudent(students, userId);
        return "Student added successfully";
    }

    @PostMapping("/addTeacher")
    @Operation(summary = "Add a Teacher")
    public String addTeacher(@RequestBody Teachers teachers) {
        teachersService.addTecher(teachers);
        return "Teacher added successfully";
    }

    @PostMapping("/addAdmin")
    @Operation(summary = "Creating an other admin")
    public String addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return "Admin created successfully";
    }

}
