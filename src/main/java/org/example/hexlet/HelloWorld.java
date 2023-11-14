package org.example.hexlet;

import io.javalin.Javalin;
import java.util.Collections;
import java.util.List;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;

public class HelloWorld {
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

//        app.get("/", ctx -> ctx.render("index.jte"));
        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParam("id");
            var courses = Data.getCourses();/* Список курсов извлекается из базы данных */
            var header = "Courses on computer programming";
            var page = new CoursePage(courses, header);
            ctx.render("index.jte", Collections.singletonMap("page", page));
        });

        app.start(7070);
    }
}
