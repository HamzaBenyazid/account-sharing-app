package com.winchesters.accountsharingapp.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    List<Request> findAllByOfferCreator(String username);
    List<Request> findAllByOfferId(Long OfferID);

}
