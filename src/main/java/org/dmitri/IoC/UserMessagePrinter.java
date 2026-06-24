package org.dmitri.IoC;

public class UserMessagePrinter {
    private final UserMessageService userMessageService;

    public UserMessagePrinter(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    public void printMessage(String name) {
        System.out.println(userMessageService.createMessage(name));
    }
}
