package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import io.javalin.http.NotFoundResponse;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.model.Course;

public class HelloWorld {
    private static final List<User> USERS = Data.getUsers();
    private static final List<Course> COURSES = Data.getCourses();
    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/users/build", ctx -> {
            var header = "Users List";
            var page = new UsersPage(USERS, header);
            ctx.render("tem-plate.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses", ctx -> {
            var header = "Courses List";
            var page = new CoursePage(COURSES, header);
            ctx.render("courses.jte", Collections.singletonMap("page", page));
        });

        app.get("/users", ctx -> {
            var header = "Users List";
            var page = new UsersPage(USERS, header);
            ctx.render("users.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            User user = USERS.stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                throw new NotFoundResponse("User not found");
            }
            var header = "User # " + id;
            var page = new UserPage(user, header);
            ctx.render("user.jte", Collections.singletonMap("page", page));
        });

        app.start(7070);
    }
}
