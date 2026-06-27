package org.dmitri.App.model;

import java.util.Objects;

public class Account {
    private static Integer count = 0;
    private Integer id;
    private Integer userId;
    private Integer moneyAmount;

    public Account(Integer userId) {
        this.id = count++;
        this.userId = userId;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(userId, account.userId) && Objects.equals(moneyAmount, account.moneyAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, moneyAmount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
