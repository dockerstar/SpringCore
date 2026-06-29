package org.dmitri.App.repository;

import org.dmitri.App.exception.NotFoundUserException;
import org.dmitri.App.model.User;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public List<User> findAll() {
        return userMap.values().stream().toList();
    }

    @Override
    public User findById(Integer userId) throws NotFoundUserException {
        for (Map.Entry<Integer, User> entry: userMap.entrySet()) {
            if (Objects.equals(entry.getValue().getId(), userId)) {
                return entry.getValue();
            }
        }
        throw new NotFoundUserException("User с таким id: %s - не найден в %s".formatted(userId,
                InMemoryUserRepository.class.getSimpleName()));
    }

    @Override
    public boolean findByLogin(String login) {
        for (Map.Entry<Integer, User> entry: userMap.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
