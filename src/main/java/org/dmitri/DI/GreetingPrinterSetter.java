package org.dmitri.DI;

import org.springframework.beans.factory.annotation.Autowired;

public class GreetingPrinterSetter {
    private GreetingService greetingService;

    public GreetingPrinterSetter(){}

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService=greetingService;
    }

    @Override
    public String toString() {
        return "GreetingPrinterSetter{" +
                "greetingService=" + greetingService +
                '}';
    }
}
