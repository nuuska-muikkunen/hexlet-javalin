package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.model.Course;
import org.example.hexlet.util.NamedRoutes;

import org.apache.commons.lang3.StringUtils;

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

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var name = ctx.queryParam("name");
            if (name == null) {
                var header = "Courses";
                var page = new CoursePage(courseRepository.getEntities(), header);
                ctx.render("courses/index.jte", Collections.singletonMap("page", page));
            } else {
                var course = courseRepository.getEntities().stream()
                        .filter(c -> c.getName().contains(name))
                        .toList();
                var header = "Course";
                var page = new CoursePage(course, header);
                ctx.render("courses/index.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.usersPath(), ctx -> {
            var id = ctx.queryParam("id");
            if (id == null) {
                var header = "User";
                var page = new UsersPage(userRepository.getEntities(), header);
                ctx.render("users/index.jte", Collections.singletonMap("page", page));
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
                ctx.render("users/show.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.usersPath(), ctx -> {
            var name = StringUtils.capitalize(ctx.formParam("name").trim());
            var email = ctx.formParam("email").trim().toLowerCase();
            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .check(value -> value.length() > 6, "У пароля недостаточная длина")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/buildcourse.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.coursesPath(), ctx -> {
            try {
                var description = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() > 10, "Course description less than 10 letters")
                        .get();
                var name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() > 2, "Course name less than 2 letters")
                        .get();
                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException e) {
                var description = ctx.formParam("description");
                var name = ctx.formParam("name");
                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/buildcourse.jte", Collections.singletonMap("page", page));
            }
        });

        app.start(7070);
    }
}
