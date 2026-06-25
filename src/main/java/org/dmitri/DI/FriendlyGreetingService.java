package org.dmitri.DI;

public class FriendlyGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hiiiiiii %s".formatted(name);
    }
}
