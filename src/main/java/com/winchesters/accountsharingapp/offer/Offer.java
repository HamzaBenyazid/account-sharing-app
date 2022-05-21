package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.request.Request;
import com.winchesters.accountsharingapp.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Offer {
    @Id
    @Column(name = "id_offer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date OfferDate;
    private Double calculatedPrice;
    private Integer maxUsers;
    private Boolean isPublic;


    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User offerer;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(
            name = "subscription",
            joinColumns = {@JoinColumn(name = "OFFER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<User> splitters;

    @OneToMany(mappedBy="offer", fetch = FetchType.LAZY)
    private List<Request> requests;

}
