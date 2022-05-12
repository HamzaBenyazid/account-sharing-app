package com.winchesters.accountsharingapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table( name = "MY_USER")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private boolean verified;
    private Date birth_date;
    private Date creation_date;
}
