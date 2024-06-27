package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
@Schema(name = "CreateFeedback", description = "CreateFeedback model")
public class CreateFeedback extends UserAuthModel {
    @NotBlank
    public String feedback;
    @NotNull
    @Min(1)
    @Max(5)
    public Integer rating;
}
