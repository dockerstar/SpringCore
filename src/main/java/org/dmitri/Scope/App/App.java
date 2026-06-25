package org.dmitri.Scope.App;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        JobRunner jobRunner1 = context.getBean(JobRunner.class);
        JobRunner jobRunner2 = context.getBean(JobRunner.class);
        jobRunner1.runOnce();
        jobRunner2.runOnce();
        context.close();
    }
}
