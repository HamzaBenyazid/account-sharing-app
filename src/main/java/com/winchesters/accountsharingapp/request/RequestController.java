package com.winchesters.accountsharingapp.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @GetMapping("")
    public List<Request> getRequestsToUser() {
        return requestService.getRequestsToUser();
    }

    @GetMapping("/{offerId}")
    public List<Request> getRequestsForOffer(@PathVariable("offerId") Long offerId) {
        return requestService.getRequestsForOffer(offerId);
    }

    @PostMapping("/{offerId}")
    public void submitRequest(@PathVariable("offerId") Long offerId) {
         requestService.createRequest(offerId);
    }

    @PostMapping("/acceptRequest/{requestId}")
    public void acceptRequest(@PathVariable("requestId") Long requestId) {
        requestService.acceptRequest(requestId);
    }

    @PostMapping("/denyRequest/{requestId}")
    public void denyRequest(@PathVariable("requestId") Long requestId) {
        requestService.denyRequest(requestId);
    }


    @DeleteMapping("{requestId}")
    public void deleteRequest(@PathVariable("requestId") Long requestId) {
        requestService.deleteRequest(requestId);
    }

}
