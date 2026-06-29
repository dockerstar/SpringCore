package org.dmitri.App.repository;

import org.dmitri.App.exception.NotFoundUserException;
import org.dmitri.App.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    User findById(Integer userId) throws NotFoundUserException;
    boolean findByLogin(String login);
}
