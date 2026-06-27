package org.dmitri.App.service;

import org.dmitri.App.config.AccountProperties;
import org.dmitri.App.exception.UserAlreadyExistsException;
import org.dmitri.App.model.Account;
import org.dmitri.App.model.User;
import org.dmitri.App.repository.AccountRepository;
import org.dmitri.App.repository.UserRepository;
import java.util.List;

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

    public User create(String login) throws UserAlreadyExistsException {
        if (userRepository.findByLogin(login)) throw new UserAlreadyExistsException("Пользователь с логином '%s' уже существует".formatted(login));
        User user = new User(login);
        Account account = new Account(user.getId());
        account.setMoneyAmount(accountProperties.getDefaultAmount());
        List<Account> accountList = user.getAccountList();
        accountList.add(account);
        user.setAccountList(accountList);
        userRepository.save(user);
        accountRepository.save(account);
        System.out.printf("User '%s' c id=%s создан\n", login, user.getId());
        return user;
    }

    public List<User> findAll() {
        if (userRepository.findAll().isEmpty()) {
            System.out.println("В базе нет пользователей\n");
        }
        return userRepository.findAll();
    }
}
