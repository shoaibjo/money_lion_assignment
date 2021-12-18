package com.moneylion.assignment.authorization.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantAccessRequest {


    @NotNull(message = "Feature Name Can Not be Null")
    @NotEmpty(message = "Feature Name Can Not be Empty")
    String featureName;

    @NotNull(message = "Email Can Not be Null")
    @NotEmpty(message = "Email Can Not be Empty")
    @Email(message = "Email is malformed")
    String email;

    @NotNull(message = "Enable Can Not be Null")
    Boolean enable;

    @Override
    public String toString() {
        return "AuthorizationDTO{" +
                "featureName='" + featureName + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                '}';
    }
}
