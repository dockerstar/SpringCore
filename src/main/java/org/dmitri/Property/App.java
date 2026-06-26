package org.dmitri.Property;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("org.dmitri.Property");

        PlannerService plannerService = context.getBean(PlannerService.class);
        plannerService.getPlanner();

        context.close();
    }
}
