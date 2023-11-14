package org.example.hexlet;

import net.datafaker.Faker;
import org.example.hexlet.model.Course;

import java.util.*;

public class Data {
    private static final int ITEMS_COUNT = 0;

    private static int idCounter = ITEMS_COUNT;

    public static List<Course> getCourses() {
        Random random = new Random(321);
        Faker faker = new Faker(new Locale("en"), random);

        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            Course course = new Course("", "");
            course.setName(faker.name().name());
            course.setDescription(faker.text().text());
            courses.add(course);
        }

        return courses;
    }
}
