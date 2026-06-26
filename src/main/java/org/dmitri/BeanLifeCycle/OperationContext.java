package org.dmitri.BeanLifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class OperationContext {
    public OperationContext() {
        System.out.println(OperationContext.class.getSimpleName() + " construct init");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(OperationContext.class.getSimpleName() + " init");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println(OperationContext.class.getSimpleName() + " destroy");
    }
}
