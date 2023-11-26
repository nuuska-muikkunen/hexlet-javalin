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
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.directory = "/css";
            });
        });

        app.get("/", ctx -> {
            ctx.render("main.jte");
        });

        app.get("/courses", ctx -> {
            var header = "Courses";
            var page = new CoursePage(COURSES, header);
            ctx.render("courses.jte", Collections.singletonMap("page", page));
        });

        app.get("/users", ctx -> {
            var id = ctx.queryParamAsClass("id", Long.class).get();
            var user = USERS.stream()
                    .filter(u -> u.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                throw new NotFoundResponse("User not found");
            }
            var header = "User";
            var page = new UserPage(user, header);
            ctx.render("user.jte", Collections.singletonMap("page", page));
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
            var header = "User";
            var page = new UserPage(user, header);
            ctx.render("user.jte", Collections.singletonMap("page", page));
        });

        app.get("/userslist", ctx -> {
            var header = "Users";
            var page = new UsersPage(USERS, header);
            ctx.render("users.jte", Collections.singletonMap("page", page));
        });

        app.start(7070);
    }
}
