package com.main.controllers;

import com.main.RequestDto.AuthRequest;
import com.main.securityConfig.jwtConfig.JwtService;
import com.main.securityConfig.userDetails.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        return jwtService.generateToken(userDetails);


    }
}
