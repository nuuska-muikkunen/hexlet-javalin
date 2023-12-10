package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.model.User;
import org.example.hexlet.model.Course;
import org.example.hexlet.util.NamedRoutes;

public class HelloWorld {
    private static final List<User> USERS = Data.getUsers();
    private static final List<Course> COURSES = Data.getCourses();
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        userRepository.setEntities(USERS);
        CourseRepository courseRepository = new CourseRepository();
        courseRepository.setEntities(COURSES);

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.directory = "/css";
            });
        });
//        app.get("/", ctx -> {
//            var visited = Boolean.valueOf(ctx.cookie("visited"));
//            var page = new MainPage(visited);
//            ctx.render("main.jte", Collections.singletonMap("page", page));
//            ctx.cookie("visited", String.valueOf(true));
//        });

        app.get("/", ctx -> {
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            ctx.render("login.jte", Collections.singletonMap("page", page));
        });

//        app.get("/", ctx -> {
//            ctx.render("main.jte");
//        });

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.get(NamedRoutes.coursePath("{name}"), CoursesController::show);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

        app.start(7070);
    }
}
