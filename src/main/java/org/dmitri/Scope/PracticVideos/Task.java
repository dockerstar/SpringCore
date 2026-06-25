package org.dmitri.Scope.PracticVideos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class Task {
    private String name = "task";
    private final LocalDateTime date;
    private Integer count = 0;

    public Task() {
        this.name = getName().concat(String.valueOf(count));
        this.date = LocalDateTime.now();
        count++;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
