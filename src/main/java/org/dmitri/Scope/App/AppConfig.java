package org.dmitri.Scope.App;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public TaskContext taskContext() {
        return new TaskContext();
    }

    @Bean
    public JobRunner jobRunner(ObjectProvider<TaskContext> objectProvider) {
        return new JobRunner(objectProvider);
    }

//    @Bean
//    public JobRunner jobRunner(TaskContext taskContext){
//        return new JobRunner(taskContext);
//    }
}
