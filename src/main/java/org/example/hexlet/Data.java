package org.example.hexlet;

import net.datafaker.Faker;
import net.datafaker.providers.base.App;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Data {
    private static final int ITEMS_COUNT = 30;
    private static int idCounter = ITEMS_COUNT;

    public static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static List<User> getUsers() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<String> ids = IntStream
                .range(1, ITEMS_COUNT + 1)
                .mapToObj(i -> Integer.toString(i))
                .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < ITEMS_COUNT; i++) {
            User user = new User();
            user.setId(Long.parseLong(ids.get(i)));
            user.setName(faker.name().name());
            user.setEmail(faker.internet().emailAddress());
            users.add(user);
        }
        return users;
    }

    public static User getUser() {
        Random random = new Random(313);
        Faker faker = new Faker(new Locale("en"), random);
        User user = new User();
        user.setId(faker.number().positive());
        user.setName(faker.name().name());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

    public static List<Course> getCourses() {
        Random random = new Random(119);
        Faker faker = new Faker(new Locale("en"), random);

        List<String> ids = IntStream
                .range(1, ITEMS_COUNT + 1)
                .mapToObj(i -> Integer.toString(i))
                .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < ITEMS_COUNT; i++) {
            Course course = new Course("", "");
            course.setId(Long.parseLong(ids.get(i)));
            course.setName(faker.animal().scientificName());
            course.setDescription(faker.disease().surgery());
            courses.add(course);
        }
        return courses;
    }
}
