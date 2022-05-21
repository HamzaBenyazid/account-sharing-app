package com.winchesters.accountsharingapp.user;


import com.winchesters.accountsharingapp.account.Account;
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
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Boolean verified;
    private Date birthDate;
    private Date creationDate;
    private ApplicationUserRole role;

    @OneToMany(mappedBy="offerer", fetch = FetchType.LAZY)
    private List<Offer> offerings;

    @OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(
            name = "subscription",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "OFFER_ID")}
    )
    private List<Offer> subscriptions;


}
