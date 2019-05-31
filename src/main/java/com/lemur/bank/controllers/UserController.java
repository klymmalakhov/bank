package com.lemur.bank.controllers;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.User;
import com.lemur.bank.model.UserPost;
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
    ResponseEntity<User> createUser(@Valid @RequestBody UserPost userPost) throws URISyntaxException {
        log.info("Request to create user: {}", userPost);
        Account account = new Account(userPost.getAmount(), userPost.getCurrency());
        account = accountRepository.save(account);
        User user = new User(
                userPost.getName(),
                userPost.getPassword(),
                userPost.getEmail(),
                userPost.getTown(),
                userPost.getCountry(),
                account
        );
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
                .body(result);
    }

}