package org.dmitri.DI;

public class FormalGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Ну здарова %s".formatted(name);
    }
}
