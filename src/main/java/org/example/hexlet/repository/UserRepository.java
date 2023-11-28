package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository {
    private static List<User> entities = new ArrayList<>();

    public static void save(User user) {
        user.setId((long) entities.size() + 1);
        entities.add(user);
    }

    public static List<User> search(String term) {
        var users = entities.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
        return users;
    }

    public static Optional<User> find(Long id) {
        var maybeUser = entities.stream()
                .filter(entity -> entity.getId() == id)
                .findAny();
        return maybeUser;
    }

    public static void delete(Long id) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id) {
                entities.remove(i);
                break;
            }
        }
    }

    public static void setEntities(List<User> entities) {
        UserRepository.entities = entities;
    }

    public static List<User> getEntities() {
        return entities;
    }
}
