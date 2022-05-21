package com.winchesters.accountsharingapp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpFormDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Date birthDate;
}
