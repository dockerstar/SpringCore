package org.dmitri.App.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private static Integer count = 0;
    private Integer id;
    private String login;
    private List<Account> accountList;

    public User() {
    }

    public User(String login) {
        this.id = count++;
        this.login = login;
        this.accountList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
