package com.winchesters.accountsharingapp.account;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.winchesters.accountsharingapp.subscription.Subscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Subscription subscription;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Credentials credentials;

    public Account(Subscription subscription) {
        this.subscription = subscription;
    }

}
