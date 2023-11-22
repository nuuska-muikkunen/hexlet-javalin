package org.example.hexlet.dto.users;
// Путь src/org/example/hexlet/dto/users/UsersPage.java
import org.example.hexlet.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
    private String header;
}
