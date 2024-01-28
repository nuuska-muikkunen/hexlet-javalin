package org.example.hexlet;

import io.javalin.Javalin;

//import java.io.File;
import java.io.IOException;
//import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
//import java.util.stream.Collectors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.hexlet.controller.CarsController;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
//import org.example.hexlet.Data;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.model.User;
import org.example.hexlet.model.Course;
import org.example.hexlet.util.NamedRoutes;

import static org.example.hexlet.Data.readResourceFile;

public class HelloWorld {
    private static final List<User> USERS = Data.getUsers();
    private static final List<Course> COURSES = Data.getCourses();
    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet_project;DB_CLOSE_DELAY=-1;");

        var dataSource = new HikariDataSource(hikariConfig);

        // Получаем путь до файла в src/main/resources

//        var url = HelloWorld.class.getClassLoader().getResource("schema.sql");
//        var file = new File(url.getFile());
//        var sql = Files.lines(file.toPath())
//                .collect(Collectors.joining("\n"));
        var sql = readResourceFile("schema.sql");
        // Получаем соединение, создаем стейтмент и выполняем запрос
        // Analog of DriverManager.getConnection("jdbc:h2:mem:hexlet_project");
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }

        BaseRepository.dataSource = dataSource;

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
// this line is just for GitHub integration test and can be removed
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

        app.get(NamedRoutes.carsPath(), CarsController::index);
        app.get(NamedRoutes.buildCarPath(), CarsController::build);
        app.get(NamedRoutes.carPath("{id}"), CarsController::show);
        app.post(NamedRoutes.carsPath(), CarsController::create);
        return app;
    }
    public static void main(String[] args) throws SQLException, IOException {
        Javalin app = getApp();
        app.start(7070);
    }
}
