package org.example.hexlet.dto.users;
// Путь src/org/example/hexlet/dto/users/UserPage.java

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hexlet.model.User;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsersPage {
    private List<User> users;
    private String header;
}
