package org.dmitri.App.repository;

import org.dmitri.App.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Integer count = 0;
    private Map<Integer, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(count, user);
        count++;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<Integer, User> entry: userMap.entrySet()) {
            userList.add(entry.getValue());
        }
        return userList;
    }
}
