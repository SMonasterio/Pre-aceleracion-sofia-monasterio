package com.DisneyApp.security.controller;

import com.DisneyApp.handler.ResponseHandler;
import com.DisneyApp.security.model.dto.AuthenticationDTORequest;
import com.DisneyApp.security.model.dto.AuthenticationDTOResponse;
import com.DisneyApp.security.model.dto.UserDTO;
import com.DisneyApp.security.model.entity.UserEntity;
import com.DisneyApp.security.service.IJwtService;
import com.DisneyApp.security.service.JwtService;
import com.DisneyApp.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationDTOResponse> signUp(@RequestBody UserDTO user) throws Exception {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTOResponse> signIn(@RequestBody AuthenticationDTORequest authRequest) throws Exception{
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails =(UserDetails) auth.getPrincipal();
        } catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationDTOResponse(jwt));

    }


}
