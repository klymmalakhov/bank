package com.lemur.bank.controllers;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.User;
import com.lemur.bank.model.UserResponse;
import com.lemur.bank.repositories.AccountRepository;
import com.lemur.bank.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserRepository userRepository;
    private AccountRepository accountRepository;


    public UserController(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;

    }

    @GetMapping("/users")
    Collection<User> users() {
        log.info("Request to get all user: " + userRepository.findAll());
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    ResponseEntity<User> createUser(@Valid @RequestBody UserResponse userResponse) throws URISyntaxException {
        log.info("Request to create user: {}", userResponse);
        Account account = new Account(userResponse.getAmount(), userResponse.getCurrency());
        account = accountRepository.save(account);
        User user = new User(
                userResponse.getName(),
                userResponse.getPassword(),
                userResponse.getEmail(),
                userResponse.getTown(),
                userResponse.getCountry(),
                account);
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
                .body(result);
    }

}