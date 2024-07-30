package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.LogoutRequestDto;
import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    UserController(UserService userService)
    {
        this.userService=userService;
    }
    @PostMapping("/signup")
    public UserDto signUpUser(@RequestBody SignUpRequestDto request)
    {
        User user=userService.signUp(request.getEmail(),request.getName(),request.getPassword());

        return UserDto.from(user);
    }

    @PostMapping("/login")
    public Token loginUser(@RequestBody LoginRequestDto loginRequestDto)
    {

        return userService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody LogoutRequestDto logoutRequestDto)
    {
        userService.logout(logoutRequestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("/validate/{token}")
    public UserDto validateToken(@PathVariable String token)
    {
        User user=userService.validateToken(token);
        return UserDto.from(user);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id)
    {
        return null;
    }
}
