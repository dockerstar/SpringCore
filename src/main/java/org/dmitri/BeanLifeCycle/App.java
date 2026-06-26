package org.dmitri.BeanLifeCycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AppLogger appLogger1 = context.getBean(AppLogger.class);
        AppLogger appLogger2 = context.getBean(AppLogger.class);

        System.out.println("AppLogger: " + (appLogger1==appLogger2));

        OperationContext operationContext1 = context.getBean(OperationContext.class);
        OperationContext operationContext2 = context.getBean(OperationContext.class);

        System.out.println("OperationContext: " + (operationContext1==operationContext2));

        context.close();
    }
}
