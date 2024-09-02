package com.example.DemoSpringSecurity.Controller;

import com.example.DemoSpringSecurity.Model.Users;
import com.example.DemoSpringSecurity.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/users")
    public ResponseEntity<Users> createPatient(@RequestBody Users users) {
        Users createdusers = usersService.createUser(users);
        return new ResponseEntity<>(createdusers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login( @RequestBody Users users){
//        System.out.println(users);
        return usersService.verify(users);
    }

}
