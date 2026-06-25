package org.dmitri.Scope.App;

public class TaskContext {
    private static Integer id = 0;

    public TaskContext() {
        this.id++;
    }

    public Integer getId() {
        return id;
    }
}
