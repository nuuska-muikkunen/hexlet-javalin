package org.example.hexlet.dto.users;
// Путь src/org/example/hexlet/dto/users/UserPage.java
import org.example.hexlet.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserPage {
    private User user;
}
