package org.dmitri.DI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        FormalGreetingService formalGreetingService = context.getBean(FormalGreetingService.class);
        FriendlyGreetingService friendlyGreetingService = context.getBean(FriendlyGreetingService.class);
        System.out.println(formalGreetingService.greet("Dima"));
        System.out.println(friendlyGreetingService.greet("Dima"));

        GreetingPrinterConstructor greetingPrinterConstructor = context.getBean(GreetingPrinterConstructor.class);
        System.out.println(greetingPrinterConstructor);
        context.close();
    }
}
