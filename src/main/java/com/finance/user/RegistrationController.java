package com.finance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private AppUserService appUserService;

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    @PostMapping
    public String register(@RequestBody AppUser appUser) {
        //appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserService.register(appUser);
        return "registered";
    }
}
