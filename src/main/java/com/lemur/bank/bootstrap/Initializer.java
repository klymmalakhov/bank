package com.lemur.bank.bootstrap;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.Event;
import com.lemur.bank.model.User;
import com.lemur.bank.repositories.AccountRepository;
import com.lemur.bank.repositories.EventRepository;
import com.lemur.bank.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Component
class Initializer implements ApplicationListener<ContextRefreshedEvent> {

    private AccountRepository accountRepository;
    private EventRepository eventRepository;
    private UserRepository userRepository;

    public Initializer(AccountRepository accountRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initUserData();
        initEventData();
        printRepo();
    }

    private void initEventData() {
        Account sourceAccount = new Account(new BigDecimal(300), Currency.getInstance("USD"));
        Account destinationAccount = new Account(new BigDecimal(100), Currency.getInstance("USD"));
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        User sourceUser = new User("password", "Johnny", "email", "Kiev", "Ukraine", sourceAccount);
        User destinationUser = new User("password", "Bill", "email", "Kiev", "Ukraine", destinationAccount);
        userRepository.save(sourceUser);
        userRepository.save(destinationUser);

        Event transfer1 = new Event(Instant.now().minusSeconds(10000000), new BigDecimal(5), Currency.getInstance("USD"), "for startup!", sourceAccount, destinationAccount);
        Event transfer2 = new Event(Instant.now().minusSeconds(5000000), new BigDecimal(5), Currency.getInstance("USD"), "for startup!", sourceAccount, destinationAccount);
        Event transfer3 = new Event(Instant.now().minusSeconds(100000), new BigDecimal(10), Currency.getInstance("USD"), "for startup!", sourceAccount, destinationAccount);
        Event transfer4 = new Event(Instant.now(), new BigDecimal(20), Currency.getInstance("USD"), "for startup!", sourceAccount, destinationAccount);
        Event transfer5 = new Event(Instant.now(), new BigDecimal(40), Currency.getInstance("USD"), "I'm reach! I don't need it!", destinationAccount, sourceAccount);
        eventRepository.save(transfer1);
        eventRepository.save(transfer2);
        eventRepository.save(transfer3);
        eventRepository.save(transfer4);
        eventRepository.save(transfer5);

    }

    private void initUserData() {
        Account account = new Account(new BigDecimal(12345), Currency.getInstance("USD"));
        accountRepository.save(account);

        User user = new User("password", "Alex", "email", "Kiev", "Ukraine", account);
        userRepository.save(user);

    }

    private void printRepo(){
        accountRepository.findAll().forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);
        eventRepository.findAll().forEach(System.out::println);
    }
}
