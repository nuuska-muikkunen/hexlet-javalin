package org.example.hexlet.dto.courses;
// Путь src/org/example/hexlet/dto/courses/CoursePage.java
import org.example.hexlet.model.Course;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoursePage {
    private List<Course> courses;
    private String header;
}
