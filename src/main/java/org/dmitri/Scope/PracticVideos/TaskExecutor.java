package org.dmitri.Scope.PracticVideos;

import org.springframework.stereotype.Component;

@Component
public class TaskExecutor {
    private final Task task;

    public TaskExecutor(Task task) {
        this.task = task;
    }

    public void executeProcess() {
        System.out.println("task name: %s, time create: %s".formatted(task.getName(), task.getDate()));
    }
}
