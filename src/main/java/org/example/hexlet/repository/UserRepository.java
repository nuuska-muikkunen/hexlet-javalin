package org.example.hexlet.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository extends BaseRepository {
    private static List<User> entities = new ArrayList<>();

    public static void save(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            // Устанавливаем ID в сохраненную сущность
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<User> search(String term) throws SQLException {
        var sql = "SELECT * FROM users WHERE name LIKE ?";
        var result = new ArrayList<User>();
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, term);
            var resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                result.add(user);
            }
            return result;
        }
    }

    public static Optional<User> find(Long id) throws SQLException {
        var sql = "SELECT * FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setLong(1, id);
            var resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                return Optional.of(user);
            }
            return Optional.empty();
        }
    }

    public static void delete(Long id) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id) {
                entities.remove(i);
                break;
            }
        }
    }

    public static void setEntities(List<User> entities) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        for (User user : entities) {
            try (var conn = dataSource.getConnection();
                 var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                prepStatement.setString(1, user.getName());
                prepStatement.setString(2, user.getEmail());
//                prepStatement.setString(3, user.getPassword());
                prepStatement.executeUpdate();
                var generatedKeys = prepStatement.getGeneratedKeys();
                // Устанавливаем ID в сохраненную сущность
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving an entity");
                }
            }
        }
    }

    public static List<User> getEntities() throws SQLException {
        var sql = "SELECT * FROM users";
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql)) {
            var resultSet = prepStatement.executeQuery();
            var result = new ArrayList<User>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                result.add(user);
            }
            return result;
        }
    }
}
