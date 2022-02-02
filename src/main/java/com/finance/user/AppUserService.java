package com.finance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(AppUser appUser) {
        // TODO: password checking
        // TODO: email checking
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        if (appUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already taken!");
        }
        appUserRepository.save(appUser);
    }

    public void delete(Long id) {
        appUserRepository.deleteById(id);
    }
}
