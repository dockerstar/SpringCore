package org.dmitri.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlannerService {
    private final PlannerProperties plannerProperties;

    public PlannerService(PlannerProperties plannerProperties) {
        this.plannerProperties=plannerProperties;
    }

    public void getPlanner() {
        System.out.println(plannerProperties.getStatus() + " " + plannerProperties.getDuration() + " " + plannerProperties.getSize());
    }
}
