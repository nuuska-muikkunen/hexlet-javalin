package org.example.hexlet.dto.users;
// Путь src/org/example/hexlet/dto/users/UsersPage.java
import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage extends BasePage {
    private List<User> users;
}
