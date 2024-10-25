package com.school_management.controller;

import com.school_management.entity.Students;
import com.school_management.service.StudentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Users APIs", description = "Get and Update the the personal info ")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class UsersController {

    private final StudentsService studentsService;

    @GetMapping("/home")
    @Operation(summary = "check the application heath")
    public String heathCheck() {
        return "App is working fine. you are in user section";
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get user info by userName")
    public Students getByName(@PathVariable String name) {
        return  studentsService.getByName(name);
    }

}
