package com.cafeteria.cafeteria.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Utils.JwtTokenUtil;
import com.cafeteria.cafeteria.ViewModels.UserLoginModel;
import com.cafeteria.cafeteria.ViewModels.UserModel;
import com.cafeteria.cafeteria.ViewModels.UserRegisterModel;
import com.cafeteria.cafeteria.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/public/users")
@Tag(name = "Users", description = "Users API for creating, getting, updating and deleting users")
public class UserController {
    
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    @Valid
    public ResponseEntity<UserModel> register(@RequestBody UserRegisterModel userRegisterModel) {
        UserModel userModel = this.userService.register(userRegisterModel);

        if (userModel != null) {
            // create token
            String token = this.jwtTokenUtil.generateTokenUser(userModel.username, "CUSTOMER");
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(userModel);
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    @PostMapping("/login")
    @Valid
    public ResponseEntity<UserModel> login(@RequestBody UserLoginModel userRegisterModel) {
        UserModel userModel = this.userService.login(userRegisterModel);
        if (userModel != null) {
            // create token
            String token = this.jwtTokenUtil.generateTokenUser(userModel.username, userModel.role);
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(userModel);
        }
        return ResponseEntity.badRequest().build();
    }
}
