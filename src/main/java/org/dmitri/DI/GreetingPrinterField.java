package org.dmitri.DI;

import org.springframework.beans.factory.annotation.Autowired;

public class GreetingPrinterField {

    @Autowired
    private GreetingService greetingService;

    public GreetingPrinterField(){
    }

    @Override
    public String toString() {
        return "GreetingPrinterField{" +
                "greetingService=" + greetingService +
                '}';
    }
}
