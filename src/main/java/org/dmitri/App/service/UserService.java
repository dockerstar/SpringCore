package org.dmitri.App.service;

import org.dmitri.App.config.AccountProperties;
import org.dmitri.App.exception.UserAlreadyExistsException;
import org.dmitri.App.model.Account;
import org.dmitri.App.model.User;
import org.dmitri.App.repository.AccountRepository;
import org.dmitri.App.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountProperties accountProperties;

    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       AccountProperties accountProperties) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountProperties = accountProperties;
    }

    public void create(String login) throws UserAlreadyExistsException {
        if (userRepository.findByLogin(login)) throw new UserAlreadyExistsException("Пользователь с логином '%s' уже существует".formatted(login));
        User user = new User(login);
        Account account = new Account(user.getId());
        account.setMoneyAmount(accountProperties.getDefaultAmount());
        List<Account> accountList = user.getAccountList();
        accountList.add(account);
        user.setAccountList(accountList);
        userRepository.save(user);
        accountRepository.save(account);
        System.out.printf("User '%s' c id=%s создан", login, user.getId());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
