package org.dmitri.AOP;

import org.springframework.stereotype.Component;

@Component
public class EmailService {
    public EmailService() {}

    @NotNullArgs
    public void sendMessage(String to, String text) {
        System.out.printf("Send message %s: %s", to, text);
    }
}
