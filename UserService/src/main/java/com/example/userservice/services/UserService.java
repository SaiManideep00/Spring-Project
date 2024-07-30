package com.example.userservice.services;

import com.example.userservice.dtos.SignUpRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.exceptions.PasswordMismatchException;
import com.example.userservice.exceptions.TokenNotFoundException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repositories.TokenRepository;
import com.example.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    private TokenRepository tokenRepository;
    UserService(BCryptPasswordEncoder bCryptPasswordEncoder,UserRepository userRepository,TokenRepository tokenRepository)
    {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.userRepository=userRepository;
        this.tokenRepository=tokenRepository;
    }
    public User signUp(String email,String name,String password)
    {
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashPassword(bCryptPasswordEncoder.encode(password));
        user.setEmailVerified(true);

        return userRepository.save(user);
    }
    private Token generateToken(User user)
    {
        Token token=new Token();
        token.setValue(RandomStringUtils.random(128));
        token.setExpiryAt(LocalDate.now().plusDays(30));
        token.setUser(user);
        return token;
    }
    public Token login(String email, String password)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        if(!bCryptPasswordEncoder.matches(password, user.getHashPassword()))
        {
            throw new PasswordMismatchException("Password do not match");
        }
        Token token=generateToken(user);
        Token savedToken=tokenRepository.save(token);
        return savedToken;

    }
    public void logout(String tokenValue)
    {
        Token token=tokenRepository.findByValueAndDeleted(tokenValue,false).orElseThrow(TokenNotFoundException::new);
        token.setDeleted(true);
        tokenRepository.save(token);

    }
    public User validateToken(String token)
    {
        Optional<Token> optionalToken=tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token,false,LocalDate.now());
        if(optionalToken.isEmpty())
            return null;
         return optionalToken.get().getUser();
    }
}
