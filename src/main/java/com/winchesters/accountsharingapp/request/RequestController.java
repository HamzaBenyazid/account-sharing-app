package com.winchesters.accountsharingapp.request;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }

}
