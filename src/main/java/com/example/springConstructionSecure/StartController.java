package com.example.springConstructionSecure;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableMethodSecurity
public class StartController {
    @GetMapping("/startPage")
    String getStartPage(){
        return "startPage";
    }
    @GetMapping("/startPage1")
    String getStartPage1(){
        return "startPage1";
    }
    @GetMapping("/startPage2")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String getStartPage2(){
        return "startPage2";
    }
    @GetMapping("/startPage3")
    String getStartPage3(){
        return "startPage3";
    }
}
