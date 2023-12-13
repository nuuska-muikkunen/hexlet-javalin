package org.example.hexlet.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository extends BaseRepository {
    private static List<Course> entities = new ArrayList<>();

    public static void save(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            // Устанавливаем ID в сохраненную сущность
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Course> search(String term) throws SQLException {
        var sql = "SELECT * FROM courses WHERE name LIKE ?";
        var result = new ArrayList<Course>();
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, term);
            System.out.println("term= " + term);
            var resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
    }
    public static Optional<Course> find(Long id) throws SQLException {
        var sql = "SELECT * FROM cars WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setLong(1, id);
            var resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                return Optional.of(course);
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

    public static List<Course> getEntities() throws SQLException {
        var sql = "SELECT * FROM courses";
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql)) {
            var resultSet = prepStatement.executeQuery();
            var result = new ArrayList<Course>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
    }
    public static void setEntities(List<Course> entities) throws SQLException {
        String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        for (Course cour : entities) {
            try (var conn = dataSource.getConnection();
                 var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                prepStatement.setString(1, cour.getName());
                prepStatement.setString(2, cour.getDescription());
                prepStatement.executeUpdate();
                var generatedKeys = prepStatement.getGeneratedKeys();
                // Устанавливаем ID в сохраненную сущность
                if (generatedKeys.next()) {
                    cour.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving an entity");
                }
            }
        }
    }
}
