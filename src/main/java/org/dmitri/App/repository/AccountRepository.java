package org.dmitri.App.repository;

import org.dmitri.App.model.Account;

public interface AccountRepository {
    void save(Account account);
    Account findById(Integer accountId);
    void delete(Integer accountId);
}
