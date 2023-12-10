@import gg.jte.Content
@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.BasePage

@param Content content
@param Content footer = null
@param BasePage page = null

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
                <a class="nav-link" href="${NamedRoutes.usersPath()}">Users</a>
                <form action="${NamedRoutes.usersPath()}" method="get">
                    <label class="form-label">
                        <input type="search" required name="id" placeholder="User id"/>
                    </label>
                    <input type="submit" value="Search"/>
                </form>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${NamedRoutes.coursesPath()}">Courses</a>
                <form action="${NamedRoutes.coursesPath()}" method="get">
                    <label class="form-label">
                        <input type="search" required name="name" placeholder="Course name"/>
                    </label>
                    <input type="submit" value="Search"/>
                </form>
            </li>
        </ul>
    </div>
</nav>
<div class="mx-auto p-4 py-md-5">
    @if(page != null && page.getFlash() != null)
        @if(!page.getFlash().contains("8"))
            <div class="alert alert-success" role="alert">
                <p>${page.getFlash()}</p>
            </div>
        @else
            <div class="alert alert-warning" role="alert">
                <p>${page.getFlash()}</p>
            </div>
        @endif
    @endif
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
