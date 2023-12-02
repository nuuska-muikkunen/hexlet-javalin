package org.example.hexlet.controller;

import java.util.Collections;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.util.NamedRoutes;

public class CoursesController {
    public static void index(Context ctx) {
        var courses = CourseRepository.getEntities();
        var page = new CoursesPage(courses);
        ctx.render("courses/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var name = ctx.pathParam("name");
        var courses = CourseRepository.search(name);
        if (courses.isEmpty()) {
            throw new NotFoundResponse("Entity with text = " + name + " not found");
        }
        var page = new CoursesPage(courses);
        ctx.render("courses/show.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        ctx.render("courses/build.jte");
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var description = ctx.formParam("description");
        var course = new Course(name, description);
        CourseRepository.save(course);
        ctx.redirect(NamedRoutes.coursesPath());
    }

}
