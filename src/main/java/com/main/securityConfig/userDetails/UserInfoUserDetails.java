package com.main.securityConfig.userDetails;


import com.main.entites.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private final String name;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserInfoUserDetails(User userInfo) {
        name=userInfo.getEmail();
        password=userInfo.getPassword();
        authorities=
                userInfo.getUserRoles().stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
                        .collect(Collectors.toList());
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
        return String.valueOf(name);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
