package com.pdfcampus.pdfcampus.controller;


import com.pdfcampus.pdfcampus.dto.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @PostMapping("/signup")
    public String execSignup(UserForm userDto){

    }
}
