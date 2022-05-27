package com.winchesters.accountsharingapp.request;

import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @Column(name = "request_id")
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
