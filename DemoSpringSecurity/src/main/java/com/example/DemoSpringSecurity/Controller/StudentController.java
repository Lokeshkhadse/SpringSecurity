package com.example.DemoSpringSecurity.Controller;

import com.example.DemoSpringSecurity.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class StudentController {
   // csrf token code
    private List<Student> students = new ArrayList<>(List.of(
            new Student(1,"loki","100"),
            new Student(2, "SHREYAS","99")
    ));

    @GetMapping("/students")
    public List<Student>getStudents(){
        return students;
    }

    @GetMapping("/csrf-token")
    public Object getCsrftoken(HttpServletRequest request){
        return request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addstudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}
