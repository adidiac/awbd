package com.cafeteria.cafeteria.ViewModels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(name = "CreateFeedback", description = "CreateFeedback model")
public class CreateFeedback extends UserAuthModel {
    public String feedback;
    public Integer rating;
}
