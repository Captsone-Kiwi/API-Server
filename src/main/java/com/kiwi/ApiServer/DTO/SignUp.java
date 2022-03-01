package com.kiwi.ApiServer.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUp {
    private String full_name;
    private String email;
    private String password;

}
