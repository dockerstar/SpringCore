package org.dmitri.App;

import org.dmitri.App.command.OperationConsoleListener;
import org.dmitri.App.config.AppConfig;
import org.dmitri.App.exception.NotNullInputLineException;
import org.dmitri.App.service.ConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        OperationConsoleListener operationConsoleListener = context.getBean(OperationConsoleListener.class);
        operationConsoleListener.listen();
        context.close();
    }
}
