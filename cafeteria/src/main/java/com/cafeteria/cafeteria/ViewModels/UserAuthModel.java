package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserAuthModel", description = "UserAuthModel model")
public class UserAuthModel {
    @NotBlank
    public String username;
}
