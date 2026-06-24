package org.dmitri.IoC;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserMessagePrinter userMessagePrinter = (UserMessagePrinter) applicationContext.getBean("print-message");
        userMessagePrinter.printMessage("Dima");
    }
}
