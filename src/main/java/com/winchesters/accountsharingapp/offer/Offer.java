package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.request.Request;
import com.winchesters.accountsharingapp.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date OfferDate;
    private Double calculatedPrice;
    private Integer maxUsers;
    private Boolean isPublic;

    @ManyToOne
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(
            name = "subscription",
            joinColumns = {@JoinColumn(name = "OFFER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<User> users;

    @OneToMany(mappedBy="offer", fetch = FetchType.LAZY)
    private List<Request> requests;

}
