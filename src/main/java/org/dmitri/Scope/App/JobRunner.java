package org.dmitri.Scope.App;

import org.springframework.beans.factory.ObjectProvider;

public class JobRunner {
//    private final TaskContext taskContext;
    private final ObjectProvider<TaskContext> objectProvider;
//
    public JobRunner(ObjectProvider<TaskContext> objectProvider) {
        this.objectProvider=objectProvider;
    }

//    public JobRunner(TaskContext taskContext) {
//        this.taskContext = taskContext;
//    }

    public void runOnce() {
        TaskContext taskContext = objectProvider.getObject();
        System.out.printf("Id task: %s%n", taskContext.getId());
    }
}
