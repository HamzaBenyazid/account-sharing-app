package com.winchesters.accountsharingapp.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/request")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @GetMapping("/myRequests")
    public List<Request> getRequestsToUser() {
        return requestService.getRequestsToUser();
    }

    @GetMapping("/{offerId}")
    public List<Request> getRequestsForOffer(@PathVariable("offerId") Long offerId) {
        return requestService.getRequestsForOffer(offerId);
    }

    @PostMapping("/g")
    public void submitRequest(@PathVariable("offerId") Long offerId) {
         requestService.createRequest(offerId);
    }

    @PostMapping("/l")
    public void acceptRequest(@PathVariable("requestId") Long requestId) {
        requestService.acceptRequest(requestId);
    }

    @PostMapping("/o")
    public void denyRequest(@PathVariable("requestId") Long requestId) {
        requestService.denyRequest(requestId);
    }


}
