@import org.example.hexlet.dto.users.UserPage

@param UserPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
<h1>${page.getHeader()}</h1>
<p>
    ${page.getUser().getId()}
    <br>
    ${page.getUser().getName()}
</p>
</body>
</html>
