package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository {
    private static List<Course> entities = new ArrayList<Course>();

    public static void save(Course course) {
        course.setId((long) entities.size() + 1);
        entities.add(course);
    }

    public static List<Course> search(String term) {
        var courses = entities.stream()
                .filter(entity -> entity.getName().contains(term))
                .toList();
        return courses;
    }

    public static Optional<Course> find(Long id) {
        var maybeCourse = entities.stream()
                .filter(entity -> entity.getId() == id)
                .findAny();
        return maybeCourse;
    }

    public static void delete(Long id) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id) {
                entities.remove(i);
                break;
            }
        }
    }

    public static List<Course> getEntities() {
        return entities;
    }

    public static void setEntities(List<Course> entities) {
        CourseRepository.entities = entities;
    }
}
