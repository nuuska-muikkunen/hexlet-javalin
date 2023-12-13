package org.example.hexlet.controller;

import java.sql.SQLException;
import java.util.Collections;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.util.NamedRoutes;

public class UsersController {
    public static void index(Context ctx) throws SQLException {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        page.setFlash(ctx.consumeSessionAttribute("flash")); // получаем значение флэш и выводим через шаблон
        ctx.render("users/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render("users/show.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("name");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");
        if (password.length() >= 8) {
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.sessionAttribute("flash", "User has been created!");
        } else {
            ctx.sessionAttribute("flash", "Password is less than 8 letters. No user created!");
        }
        ctx.redirect(NamedRoutes.usersPath());
    }

    public static void edit(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render("users/edit.jte", Collections.singletonMap("page", page));
    }


    public static void update(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        var name = ctx.formParam("name");
        var email = ctx.formParam("email");
        var password = ctx.formParam("password");

        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        UserRepository.save(user);
        ctx.redirect(NamedRoutes.usersPath());
    }

    public static void destroy(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        UserRepository.delete(id);
        ctx.redirect(NamedRoutes.usersPath());
    }
}
