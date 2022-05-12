package com.winchesters.accountsharingapp.request;

import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date requestDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private Date nextPaymentDate;

    @ManyToOne
    private User requester;

    @ManyToOne
    private Offer offer;
}
