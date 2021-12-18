package com.moneylion.assignment.authorization.model.dao;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
public class Authorization {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    int id;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String feature;

    @Getter
    @Setter
    boolean enable;

    public Authorization(String email, String feature, boolean enable) {
        this.email = email;
        this.feature = feature;
        this.enable = enable;
    }

    public boolean getEnable() {
        return enable;
    }
}
