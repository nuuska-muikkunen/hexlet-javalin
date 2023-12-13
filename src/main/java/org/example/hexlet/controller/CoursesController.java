package org.example.hexlet.controller;

import  java.util.Collections;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.util.NamedRoutes;

import java.sql.SQLException;

public class CoursesController {
    public static void index(Context ctx) throws SQLException {
        var courses = CourseRepository.getEntities();
        var page = new CoursesPage(courses);
        page.setFlash(ctx.consumeSessionAttribute("flash")); // получаем значение флэш и выводим через шаблон
        ctx.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var name = ctx.pathParam("name");
        var courses = CourseRepository.search(name);
        if (courses.isEmpty()) {
            throw new NotFoundResponse("Entity with name = " + name + " not found");
        }
        var page = new CoursesPage(courses);
        ctx.render("courses/show.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) throws SQLException {
        ctx.render("courses/build.jte");
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");
        var course = new Course(name, description);
        CourseRepository.save(course);
        // Добавляем сообщение в сессию
        // Ключ может иметь любое название, здесь мы выбрали flash
        ctx.sessionAttribute("flash", "Course has been created!");
        ctx.redirect(NamedRoutes.coursesPath());
    }
}
