package com.winchesters.accountsharingapp.user;


import com.winchesters.accountsharingapp.offer.Offer;
import com.winchesters.accountsharingapp.security.ApplicationUserRole;
import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "MY_USER")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Boolean verified;
    private Date birth_date;
    private Date creation_date;
    private ApplicationUserRole role;

    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<Offer> offerings;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(
            name = "subscription",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "OFFER_ID")}
    )
    private List<Offer> subscriptions;


}
