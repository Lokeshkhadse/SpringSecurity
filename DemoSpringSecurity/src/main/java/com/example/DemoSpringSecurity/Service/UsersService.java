package com.example.DemoSpringSecurity.Service;

import com.example.DemoSpringSecurity.Model.Users;

public interface UsersService {
    Users createUser(Users users);

    String verify(Users users);
}
