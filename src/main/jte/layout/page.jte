@import gg.jte.Content

@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/userslist">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/courses">Courses</a>
            </li>
            <li class="nav-item">
                <form action="/users" method="get">
                    <label>
                        <input type="search" required name="id" placeholder="User id"/>
                    </label>
                    <input type="submit" value="Search"/>
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="mx-auto p-4 py-md-5">
    ${content}
</div>

@if (footer != null)
    <footer class="text-center text-lg-start bg-light text-muted">
        <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
            ${footer}
        </div>
    </footer>
@endif

</body>
</html>
