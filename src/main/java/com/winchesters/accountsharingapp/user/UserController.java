package com.winchesters.accountsharingapp.user;

import com.winchesters.accountsharingapp.dto.OfferResponseDto;
import com.winchesters.accountsharingapp.dto.SignUpFormDto;
import com.winchesters.accountsharingapp.dto.OfferDto;
import com.winchesters.accountsharingapp.dto.UserResponseDto;

import com.winchesters.accountsharingapp.mapper.EntityToDtoMapper;
import com.winchesters.accountsharingapp.offer.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OfferService offerService;
    private final int pageSize=16;

    @GetMapping(path ="users")
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(headers={"target=adminCreateUser"})
    public UserResponseDto adminCreateUser(@RequestBody User user){
        return userService.adminCreateUser(user);
    }

    @PostMapping(path = {"signup"})
    public UserResponseDto signUp(@RequestBody SignUpFormDto signupForm){
        return userService.singUp(signupForm);
    }

    @RequestMapping(method = RequestMethod.PUT, path="{userId}",headers={"target=updateEmail"})
    public void updateEmail(@PathVariable Long userId,@RequestBody String email){
        userService.updateEmail(userId, email);
    }
    @GetMapping("{username}/offers")
    public List<OfferResponseDto> getUserOffers(@PathVariable String username, @RequestParam int pageNumber){
        return offerService.getOffersByOfferer(username, pageNumber,pageSize).stream().map(EntityToDtoMapper::offerToOfferResponseDto).collect(Collectors.toList());
    }

    @GetMapping("/offers")
    public List<OfferDto> listUserOffers(){
        return userService.listUserOffers();
    }

    @GetMapping
    public UserResponseDto profile(){
        return userService.getUserResponseDto();
    }
}