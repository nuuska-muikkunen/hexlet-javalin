@import org.example.hexlet.dto.courses.CoursePage
@import gg.jte.Content

@param Content content
@param CoursePage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
    <link rel="stylesheet" href="../../resources/css/style.css" type="text/css">
</head>
<body>
    <div>
        <h3>Hexlet Javalin Example</h3>
        <a href="/users">Users</a>
        <a href="/courses">Courses</a>
    </div>
    ${content}
</body>
</html>
