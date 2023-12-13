package org.example.hexlet.dto.courses;
// Путь src/org/example/hexlet/dto/courses/CoursePage.java
import org.example.hexlet.dto.BasePage;
import org.example.hexlet.model.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoursePage extends BasePage {
    private Course course;
}
