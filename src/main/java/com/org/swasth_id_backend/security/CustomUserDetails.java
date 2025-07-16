package com.org.swasth_id_backend.security;

import com.org.swasth_id_backend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can customize roles here
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // or user.getUsername() if that's your login field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // customize if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // customize if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // customize if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // customize if needed
    }
}

