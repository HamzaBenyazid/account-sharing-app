package com.winchesters.accountsharingapp.account;

import com.winchesters.accountsharingapp.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByOwnerUsername(String username);
}
