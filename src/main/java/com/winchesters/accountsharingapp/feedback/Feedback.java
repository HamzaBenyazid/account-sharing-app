package com.winchesters.accountsharingapp.feedback;

import com.winchesters.accountsharingapp.feedback.Rating;

import javax.persistence.*;

@Entity
@Table
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Rating rating;

    private String description;
}
