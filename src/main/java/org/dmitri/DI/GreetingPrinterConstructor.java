package org.dmitri.DI;

public class GreetingPrinterConstructor {
    private final GreetingService greetingService;

    public GreetingPrinterConstructor(GreetingService greetingService) {
        this.greetingService=greetingService;
    }

    @Override
    public String toString() {
        return "GreetingPrinterConstructor{" +
                "greetingService=" + greetingService +
                '}';
    }
}
