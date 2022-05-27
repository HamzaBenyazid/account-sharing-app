package com.winchesters.accountsharingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SignUpFormDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
