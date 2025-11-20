package com.example.springboot_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")

public class StudentController {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String StudentHome(){
        return "welcome to student home page";
    }
}
