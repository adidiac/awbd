package com.cafeteria.cafeteria.service;

import com.cafeteria.cafeteria.ViewModels.UserLoginModel;
import com.cafeteria.cafeteria.ViewModels.UserModel;
import com.cafeteria.cafeteria.ViewModels.UserRegisterModel;

public interface UserService {
    public UserModel register(UserRegisterModel userRegisterModel);
    public UserModel login(UserLoginModel userLoginModel);
    public Long getUserIdByUsername(String username);
}
