package org.dmitri.AOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.dmitri.AOP");

        EmailService emailService = context.getBean(EmailService.class);

        try {
            emailService.sendMessage("null", null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        context.close();
    }
}
