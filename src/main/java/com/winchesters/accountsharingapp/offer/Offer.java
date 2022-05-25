package com.winchesters.accountsharingapp.offer;

import com.winchesters.accountsharingapp.account.Account;
import com.winchesters.accountsharingapp.request.Request;
import com.winchesters.accountsharingapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Offer {
    @Id
    @Column(name = "offer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date uploadDate;
    private Double calculatedPrice;
    private Integer maxSplitters;
    private Boolean isPublic;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User offerer;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(
            name = "subscription",
            joinColumns = {@JoinColumn(name = "OFFER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private List<User> splitters = new ArrayList<>();

    @OneToMany(mappedBy="offer", fetch = FetchType.LAZY)
    private List<Request> requests= new ArrayList<>();;

    public void removeSplitter(String splitterUsername){
        this.setSplitters(this.getSplitters().stream().filter((splitter)->!splitter.getUsername().equals(splitterUsername)).toList());
    }

}
