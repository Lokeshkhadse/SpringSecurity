package com.example.DemoSpringSecurity.Service;

import com.example.DemoSpringSecurity.Model.UserPrincipal;
import com.example.DemoSpringSecurity.Model.Users;
import com.example.DemoSpringSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDeatilsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("user not found");
            throw  new UsernameNotFoundException("user not found");

        }
        return new UserPrincipal(user);
    }
}
