package org.dmitri.App.service;

import org.dmitri.App.repository.AccountRepository;
import org.dmitri.App.repository.UserRepository;

public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }
}
