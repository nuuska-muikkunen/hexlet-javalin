@import org.example.hexlet.util.NamedRoutes

<form action="${NamedRoutes.sessionsPath()}" method="post">
    <label>
        Nickname
        <input type="text" placeholder="Nickname" name="nickname" />
    </label>
    <label>
        Password
        <input type="password" placeholder="Password" name="password" />
    </label>
    <input type="submit" />
</form>
