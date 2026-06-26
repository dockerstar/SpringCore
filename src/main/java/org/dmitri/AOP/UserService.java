package org.dmitri.AOP;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    private String nameDefault = "DefaultName";
    private static Integer id = 0;
    public UserService() {
        id++;
    }

    @NotNullArgs
    public void updateName(Integer id, String newName) {
           nameDefault = newName;
    }
}
