package com.exam.controller;

import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.security.JwtUtil;
import com.exam.security.UserSecurityImpl;
import com.exam.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserSecurityImpl userService;
    @Autowired
    private JwtUtil jwtUtil;


    private void authenticate(String username,String password) throws Exception
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }
        catch (DisabledException e)
        {
            throw new Exception("User Disabled" + e.getMessage());
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Invalid Credentials" + e.getMessage());
        }
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticate(request.getUsername(),request.getPassword());
        }
        catch (Exception e)
        {
            e.printStackTrace();
          //  throw new Exception("User Not Found");
            return new  ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
