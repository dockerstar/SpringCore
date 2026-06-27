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
        User user = userRepository.findById(userId);
        Account account = new Account(userId);
        account.setMoneyAmount(accountProperties.getDefaultAmount());
        accountRepository.save(account);
        List<Account> accountList = user.getAccountList();
        accountList.add(account);
        user.setAccountList(accountList);
        System.out.printf("Account for %s created\n%s", user.getLogin(), account);
    }

    public void deposit(Integer accountId, Integer amountDeposit) throws IncorrectAmountValueException, NotFoundAccountException {
        if (amountDeposit < 0)
            throw new IncorrectAmountValueException("Сумма пополнение не может быть меньше либо равна 0");

        Account account = accountRepository.findById(accountId);
        account.setMoneyAmount(account.getMoneyAmount() + amountDeposit);
        accountRepository.save(account);
        System.out.printf("Счет c id:%s пополнен\n%s", accountId, account);
    }

    public void withdraw(Integer accountId, Integer amountWithdraw) throws IncorrectAmountValueException, NotFoundAccountException {
        if (amountWithdraw < 0)
            throw new IncorrectAmountValueException("Сумма снятия не может быть меньше либо равна 0");
        Account account = accountRepository.findById(accountId);
        if (account.getMoneyAmount() < amountWithdraw)
            throw new IncorrectAmountValueException("Сумма снятия не может быть больше текущей суммы на счете = '%s' либо равна 0"
                    .formatted(account.getMoneyAmount()));
        account.setMoneyAmount(account.getMoneyAmount() - amountWithdraw);
        System.out.printf("С счета: %s успешна списана сумма: %s\n%s", accountId, amountWithdraw, account);
    }


    public void transfer(Integer fromId, Integer toId, Integer amountTransfer) throws IncorrectAmountValueException, NotFoundAccountException {
        Account accountFrom = accountRepository.findById(fromId);
        Account accountTo = accountRepository.findById(toId);
        if (accountFrom.getMoneyAmount() < amountTransfer || amountTransfer<=0)
            throw new IncorrectAmountValueException("Сумма перевода не может быть больше текущей суммы на счете = '%s' либо меньше или равной 0"
                    .formatted(accountFrom.getMoneyAmount()));
        if (Objects.equals(accountFrom.getUserId(), accountTo.getUserId())) {
            accountFrom.setMoneyAmount(accountFrom.getMoneyAmount()-amountTransfer);
            accountTo.setMoneyAmount(accountTo.getMoneyAmount()+amountTransfer);
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
            System.out.printf("Сумма %s между счетами %s и %s пользователя с id:%s успешно переведена\n%s\n%s",
                    amountTransfer,
                    fromId, toId,
                    accountFrom.getUserId(),
                    accountFrom,
                    accountTo);
        } else {
            accountFrom.setMoneyAmount(accountFrom.getMoneyAmount()-(amountTransfer+(int)(amountTransfer*accountProperties.getTransferCommission())));
            accountTo.setMoneyAmount(accountTo.getMoneyAmount()+(amountTransfer-(int)(amountTransfer*accountProperties.getTransferCommission())));
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
            System.out.printf("Сумма %s между счетами %s и %s успешно переведена\n%s\n%s",
                    amountTransfer,
                    fromId, toId,
                    accountFrom,
                    accountTo);
        }
    }

    public void close(Integer accountId) throws NotFoundAccountException, NotFoundUserException, OneAccountUserException {
        Account account = accountRepository.findById(accountId);
        Integer userId = account.getUserId();
        User user = userRepository.findById(userId);
        List<Account> accountList = user.getAccountList();
        if (accountList.size()>=2) {
            accountList.remove(account);
            user.setAccountList(accountList);
            List<Account> accountListNew = user.getAccountList();
            Account transferAccount = accountListNew.getFirst();
            transferAccount.setMoneyAmount(transferAccount.getMoneyAmount()+account.getMoneyAmount());
            accountListNew.set(0, transferAccount);
            user.setAccountList(accountListNew);
            accountRepository.delete(accountId);
            System.out.printf("Счет %s пользователя с id:%s успешно удален\nDELETED-%s", accountId, userId, account);
        } else {
            throw new OneAccountUserException("У пользователя остался один аккаунт\n%s".formatted(user));
        }
    }
}
