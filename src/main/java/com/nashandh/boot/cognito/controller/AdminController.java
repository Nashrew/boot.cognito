package com.nashandh.boot.cognito.controller;

import com.nashandh.boot.cognito.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Generally speaking, this wouldn't exist in the API.
//  Ideally, a frontend exists with an auth flow that authenticates via Cognito
//  and attaches a valid token to any requests to this service.
//
// This controller is so I don't have to do CSS :)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // Admin service to be forbidden in the future for standard users (maybe?)
    //  -- might change this - get rid of it and just use standard auth with a couple different perms
    //  -- it's a good initial test of AWS SDK for the moment
    //
    //  -- likely will want an accessible /login here though since we won't have a UI connected
    //      to cognito for token-getting

    @Autowired
    AuthService authService;

    // TODO This endpoint simply meant to test AWS SDK initailly, disable when configuring security
    @GetMapping("/userpools")
    public ResponseEntity displayUserPools() {
        authService.listUserPools();
        return ResponseEntity.ok().build(); // :vaultboy:
    }

    // NYI - coming soon (TM)
//    @PostMapping
//    public ResponseEntity<LoginResponse> login(LoginRequest request) {
//        // do login things
//        return LoginResponse.builder.build!
//    }
}
