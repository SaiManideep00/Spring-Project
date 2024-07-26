package com.example.userservice.services;

import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    UserService(BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    public User signUp(String email,String name,String password)
    {
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashPassword(bCryptPasswordEncoder.encode(password));

        return null;
    }
    public Token login(String email, String password)
    {
        return null;
    }
    public void logout(String token)
    {

    }
    public User validateToken(String token)
    {
        return null;
    }
}
