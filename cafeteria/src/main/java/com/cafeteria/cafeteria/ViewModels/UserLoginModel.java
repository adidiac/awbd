package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserLoginModel", description = "UserLoginModel model")
public class UserLoginModel {
    @NotBlank
    @Size(max = 100)
    public String username = "";
    @NotBlank
    @Size(max = 100)
    @Size(min = 4)
    public String password = "";
}
