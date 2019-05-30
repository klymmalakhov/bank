package com.lemur.bank.bootstrap;

import com.lemur.bank.model.Account;
import com.lemur.bank.model.User;
import com.lemur.bank.repositories.AccountRepository;
import com.lemur.bank.repositories.EventRepository;
import com.lemur.bank.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        initData();
    }

    private void initData() {
        Account account = new Account(new BigDecimal(12345), Currency.getInstance("USD"));
        accountRepository.save(account);

        User user = new User("name", "password", "email", "Kiev", "Ukraine", account);
        userRepository.save(user);

        accountRepository.findAll().forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);
        eventRepository.findAll().forEach(System.out::println);
    }
}
