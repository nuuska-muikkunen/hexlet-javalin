package org.example.hexlet.dto.courses;
// Путь src/org/example/hexlet/dto/courses/CoursesPage.java
import org.example.hexlet.model.Course;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoursesPage {
    private List<Course> courses;
}
