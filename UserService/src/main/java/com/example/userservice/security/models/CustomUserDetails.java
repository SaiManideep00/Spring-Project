package com.example.userservice.security.models;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.apache.bcel.generic.LocalVariableGen;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean  accountNonExpired;
    private boolean  accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> authorities;
    private Long userId;
    public CustomUserDetails(User user)
    {
        this.username=user.getEmail();
        this.password=user.getHashPassword();
        this.accountNonExpired=true;
        this.enabled=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.authorities=new ArrayList<>();
        for(Role role:user.getRoles())
        {
            this.authorities.add(new CustomGrantedAuthority(role));
        }
        this.userId=user.getId();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public Long getUserId()
    {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
