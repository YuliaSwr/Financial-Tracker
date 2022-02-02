package com.finance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody AppUser appUser) {
        appUserService.register(appUser);
        return "registered";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        appUserService.delete(id);
        return "User deleted!";
    }
}
