package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import io.javalin.http.NotFoundResponse;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.model.Course;

public class HelloWorld {
    private static final List<User> USERS = Data.getUsers();
    private static final List<Course> COURSES = Data.getCourses();
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        CourseRepository courseRepository = new CourseRepository();
        userRepository.setEntities(USERS);
        courseRepository.setEntities(COURSES);

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
            var name = ctx.queryParam("name");
            if (name == null) {
                var header = "Courses";
                var page = new CoursePage(courseRepository.getEntities(), header);
                ctx.render("courses.jte", Collections.singletonMap("page", page));
            } else {
                var course = courseRepository.getEntities().stream()
                        .filter(c -> c.getName().contains(name))
                        .toList();
                var header = "Course";
                var page = new CoursePage(course, header);
                ctx.render("courses.jte", Collections.singletonMap("page", page));
            }
        });

        app.get("/users", ctx -> {
            var id = ctx.queryParam("id");
            if (id == null) {
                var header = "User";
                var page = new UsersPage(userRepository.getEntities(), header);
                ctx.render("users.jte", Collections.singletonMap("page", page));
            } else {
                var user = userRepository.getEntities().stream()
                        .filter(u -> id.equals(String.valueOf(u.getId())))
                        .findFirst()
                        .orElse(null);
                if (user == null) {
                    throw new NotFoundResponse("User not found");
                }
                var header = "User";
                var page = new UserPage(user, header);
                ctx.render("user.jte", Collections.singletonMap("page", page));
            }
        });

        app.get("/users/new", ctx -> {
            ctx.render("users/new.jte");
        });

        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = ctx.formParam("password");
            var passwordConfirmation = ctx.formParam("passwordConfirmation").trim().toLowerCase();
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/courses/new", ctx -> {
            ctx.render("courses/newcourse.jte");
        });

        app.post("/courses", ctx -> {
            var name = ctx.formParam("name").trim();
            var description = ctx.formParam("description").trim();
            var course = new Course(name, description);
            CourseRepository.save(course);
            ctx.redirect("/courses");
        });

        app.start(7070);
    }
}
