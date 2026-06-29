package org.dmitri.App.repository;

import org.dmitri.App.exception.NotFoundAccountException;
import org.dmitri.App.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<Integer, Account> accountMap = new HashMap<>();

    @Override
    public void save(Account account) {
        accountMap.putIfAbsent(account.getId(), account);
    }

    @Override
    public Account findById(Integer accountId) throws NotFoundAccountException {
//        for (Map.Entry<Integer, Account> entry: accountMap.entrySet()) {
//            if (Objects.equals(entry.getValue().getId(), accountId)) {
//                return entry.getValue();
//            }
//        }
        if (accountMap.containsKey(accountId)) {
            return accountMap.get(accountId);
        }
        throw new NotFoundAccountException("Аккаунт с таким id не найден в: " + InMemoryUserRepository.class.getSimpleName());
    }

    @Override
    public void delete(Integer accountId) throws NotFoundAccountException {
        if (accountMap.containsKey(accountId)) {
            accountMap.remove(accountId);
        } else {
            throw new NotFoundAccountException("Аккаунт с таким id не найден в: " + InMemoryUserRepository.class.getSimpleName());
        }

    }
}
