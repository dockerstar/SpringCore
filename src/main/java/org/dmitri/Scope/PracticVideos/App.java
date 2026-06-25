package org.dmitri.Scope.PracticVideos;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("org.dmitri.Scope.PracticVideos");
        Task task1 = context.getBean(Task.class);
        Task task2 = context.getBean(Task.class);
        System.out.println(task1 + " " + task2);
        System.out.println(task1==task2);
    }
}
