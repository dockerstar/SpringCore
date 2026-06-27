package org.dmitri.App.service;

import org.dmitri.App.config.AccountProperties;
import org.dmitri.App.exception.IncorrectAmountValueException;
import org.dmitri.App.exception.NotFoundAccountException;
import org.dmitri.App.exception.NotFoundUserException;
import org.dmitri.App.exception.OneAccountUserException;
import org.dmitri.App.model.Account;
import org.dmitri.App.model.User;
import org.dmitri.App.repository.AccountRepository;
import org.dmitri.App.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountProperties accountProperties;

    public AccountService(UserRepository userRepository,
                          AccountRepository accountRepository,
                          AccountProperties accountProperties) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountProperties = accountProperties;
    }

    public void create(Integer userId) throws NotFoundUserException {
        Account account = new Account(userId);
        account.setMoneyAmount(accountProperties.getDefaultAmount());
        accountRepository.save(account);
        User user = userRepository.findById(userId);
        List<Account> accountList = user.getAccountList();
        accountList.add(account);
        user.setAccountList(accountList);
    }

    public void deposit(Integer accountId, Integer amountDeposit) throws IncorrectAmountValueException, NotFoundAccountException {
        if (amountDeposit < 0)
            throw new IncorrectAmountValueException("Сумма пополнение не может быть меньше либо равна 0");

        Account account = accountRepository.findById(accountId);
        account.setMoneyAmount(account.getMoneyAmount() + amountDeposit);
        accountRepository.save(account);
    }

    public void withdraw(Integer accountId, Integer amountWithdraw) throws IncorrectAmountValueException, NotFoundAccountException {
        if (amountWithdraw < 0)
            throw new IncorrectAmountValueException("Сумма снятия не может быть меньше либо равна 0");
        Account account = accountRepository.findById(accountId);
        if (account.getMoneyAmount() < amountWithdraw)
            throw new IncorrectAmountValueException("Сумма снятия не может быть больше текущей суммы на счете = '%s' либо равна 0"
                    .formatted(account.getMoneyAmount()));
        account.setMoneyAmount(account.getMoneyAmount() - amountWithdraw);
    }


    public void transfer(Integer fromId, Integer toId, Integer amountTransfer) throws IncorrectAmountValueException, NotFoundAccountException {
        Account accountFrom = accountRepository.findById(fromId);
        Account accountTo = accountRepository.findById(toId);
        if (accountFrom.getMoneyAmount() < amountTransfer)
            throw new IncorrectAmountValueException("Сумма перевода не может быть больше текущей суммы на счете = '%s' либо равна 0"
                    .formatted(accountFrom.getMoneyAmount()));
        if (Objects.equals(accountFrom.getUserId(), accountTo.getUserId())) {
            accountFrom.setMoneyAmount(accountFrom.getMoneyAmount()-amountTransfer);
            accountTo.setMoneyAmount(accountTo.getMoneyAmount()+amountTransfer);
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
        } else {
            accountFrom.setMoneyAmount(accountFrom.getMoneyAmount()-(amountTransfer+(int)(amountTransfer*accountProperties.getTransferCommission())));
            accountTo.setMoneyAmount(accountTo.getMoneyAmount()+(amountTransfer-(int)(amountTransfer*accountProperties.getTransferCommission())));
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
        }
    }

    public void close(Integer accountId) throws NotFoundAccountException, NotFoundUserException, OneAccountUserException {
        Account account = accountRepository.findById(accountId);
        Integer userId = account.getUserId();
        List<Account> accountList = userRepository.findById(userId).getAccountList();
        if (accountList.size()>=2) {
            accountRepository.delete(accountId);
        } else {
            throw new OneAccountUserException("У пользователя остался один аккаунт");
        }
    }
}
