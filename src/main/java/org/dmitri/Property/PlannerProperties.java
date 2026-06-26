package org.dmitri.Property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class PlannerProperties {
    @Value("${planner.enabled:false}")
    private Boolean status;

    @Value("${planner.default-duration:0}")
    private Integer duration;

    @Value("${planner.batch-size: 0}")
    private Integer size;

    public Boolean getStatus() {
        return status;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getSize() {
        return size;
    }
}
