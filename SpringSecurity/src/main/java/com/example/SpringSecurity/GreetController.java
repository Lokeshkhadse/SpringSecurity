package com.example.SpringSecurity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class GreetController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @PreAuthorize("hasRole('USER1')")
    @GetMapping("/user1")
    public String user1(){
        return "hello user1";
    }

    @GetMapping("/user2")
    public String user2(){
        return "hello user2";
    }

    //csrf token code
//    private List<Student> students = new ArrayList<>(List.of(
//            new Student(1,"loki"),
//            new Student(2, "SHREYAS")
//    ));
//
//    @GetMapping
//    public List<Student>getStudents(){
//        return students;
//    }
//
//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

}
