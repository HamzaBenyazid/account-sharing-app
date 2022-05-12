package com.winchesters.accountsharingapp.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Feedback {
    @Id
    private long id;

    @Embedded
    private Rating rating;

    private String description;
}
