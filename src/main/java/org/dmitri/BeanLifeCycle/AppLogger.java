package org.dmitri.BeanLifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class AppLogger {

    public AppLogger() {
        System.out.println(AppLogger.class.getSimpleName() + " construct init");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(AppLogger.class.getSimpleName() + " init");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println(AppLogger.class.getSimpleName() + " destroy");
    }
}
