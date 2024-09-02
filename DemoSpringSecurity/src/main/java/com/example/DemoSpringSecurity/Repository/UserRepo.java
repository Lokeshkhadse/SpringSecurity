package com.example.DemoSpringSecurity.Repository;

import com.example.DemoSpringSecurity.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
}
