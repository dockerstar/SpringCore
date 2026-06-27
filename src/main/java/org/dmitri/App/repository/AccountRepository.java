package org.dmitri.App.repository;

import org.dmitri.App.exception.NotFoundAccountException;
import org.dmitri.App.model.Account;

public interface AccountRepository {
    void save(Account account);
    Account findById(Integer accountId) throws NotFoundAccountException;
    void delete(Integer accountId) throws NotFoundAccountException;
}
