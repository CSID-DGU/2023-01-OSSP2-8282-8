package com.pdfcampus.pdfcampus.controller;


import com.pdfcampus.pdfcampus.dto.UserForm;
import com.pdfcampus.pdfcampus.service.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    private SignupService signupService;

    @PostMapping("/signup")
    public String execSignup(UserForm userDto){
        signupService.joinUser(userDto);

        return "redirect:/login";
    }
}
