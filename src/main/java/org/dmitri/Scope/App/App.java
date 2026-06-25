package org.dmitri.Scope.App;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        JobRunner jobRunner = context.getBean(JobRunner.class);
        jobRunner.runOnce();
        jobRunner.runOnce();
        jobRunner.runOnce();
        jobRunner.runOnce();
        jobRunner.runOnce();
        context.close();
    }
}
