package com.lemur.bank.controllers;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.Event;
import com.lemur.bank.model.EventPost;
import com.lemur.bank.repositories.AccountRepository;
import com.lemur.bank.repositories.EventRepository;
import com.lemur.bank.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);
    private EventRepository eventRepository;
    private AccountRepository accountRepository;
    private UserRepository userRepository;


    public EventController(EventRepository eventRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/events")
    Collection<Event> event(@RequestParam Long userId,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate) {
        log.info("Request to get events: for user '{}' in period from '{}' till '{}'", userId, startDate, endDate);
        ArrayList<Account> userAccounts = (ArrayList<Account>) userRepository.findAllAccountsForUser(userId);
        Account account = userAccounts.get(0);

        return eventRepository.findAllEventsByAccount(account.getId());
    }





    @GetMapping("/event/{id}")
    ResponseEntity<?> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/event")
    ResponseEntity<Event> createEvent(@Valid @RequestBody EventPost eventPost) throws URISyntaxException {
        log.info("Request to create even: '{}' ", eventRepository);
        Optional<Account> sourceAccount = accountRepository.findById(eventPost.getSource());
        Optional<Account> destinationAccount = accountRepository.findById(eventPost.getDestination());
        Instant date = Instant.now();
        Event event = new Event(
                date,
                eventPost.getAmount(),
                eventPost.getCurrency(),
                eventPost.getDescription(),
                sourceAccount.get(),
                destinationAccount.get()
        );
        Event result = eventRepository.save(event);
        return ResponseEntity.created(new URI("/api/event/" + result.getId()))
                .body(result);
    }

}