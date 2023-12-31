package com.example.javatutorial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.javatutorial.exception.NotAuthorizedException;
import com.sap.cloud.security.xsuaa.token.Token;

@RestController
@RequestMapping(path = "/v1")
public class MainController {

    @GetMapping(path = "/hello")
    public ResponseEntity<String> readAll(@AuthenticationPrincipal Token token) {
        if (!token.getAuthorities().contains(new SimpleGrantedAuthority("Display"))) {
            throw new NotAuthorizedException("This operation requires \"Display\" scope");
        }

        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }
}
