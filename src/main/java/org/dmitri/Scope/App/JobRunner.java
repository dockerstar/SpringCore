package org.dmitri.Scope.App;

public class JobRunner {
    private final TaskContext taskContext;

    public JobRunner(TaskContext taskContext) {
        this.taskContext = taskContext;
    }

    public void runOnce() {
        System.out.printf("Id task: %s%n", taskContext.getId());
    }
}
