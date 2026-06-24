package org.dmitri.IoC;

public class UserMessageService {
    public String createMessage(String name) {
         return "Привет, %s, добро пожаловать".formatted(name);
    }
}
