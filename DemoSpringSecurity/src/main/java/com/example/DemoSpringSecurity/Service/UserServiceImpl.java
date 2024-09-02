package com.example.DemoSpringSecurity.Service;

import com.example.DemoSpringSecurity.Model.Users;
import com.example.DemoSpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UsersService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);//12 rounds hashcode
    @Override
    public Users createUser(Users users) {
        // jo pn pass postman vr taknar to bhetnar ithe and tyla encode karicha and set karicha tya user la
        users.setPassword(encoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

    @Override
    public String verify(Users users) {
        Authentication authentication =
                authenticationManager.
                        authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
         if(authentication.isAuthenticated()){
             return  jwtService.generateToken(users.getUsername());
         }
         return "fail";

    }
}
