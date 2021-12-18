package com.moneylion.assignment.authorization.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanAccessDto {

    @NotEmpty(message = "Feature Can not be empty")
    @NotNull(message = "Feature Can not be null")
    private String feature;

    @NotEmpty(message = "Email Can not be empty")
    @NotNull(message = "Email Can not be null")
    @Email(message = "Email is malformed")
    private String email;
}
