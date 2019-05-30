package com.lemur.bank.controllers;

import com.lemur.bank.model.Account;
import com.lemur.bank.repositories.AccountRepository;
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
class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);
    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    Collection<Account> accounts() {
        log.info("Request to get all accounts: " + accountRepository.findAll());
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    ResponseEntity<?> getAccount(@PathVariable Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/account")
    ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) throws URISyntaxException {
        log.info("Request to create account: {}", account);
        Account result = accountRepository.save(account);
        return ResponseEntity.created(new URI("/api/account/" + result.getId()))
                .body(result);
    }

    @PutMapping("/account/{id}")
    ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account) {
        log.info("Request to update account: {}", account);
        Account result = accountRepository.save(account);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        log.info("Request to delete account: {}", id);
        accountRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}