<!-- Изменения HTML в шаблоне не требуют перезапуска сервера -->
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
<h1>${page.getHeader()}</h1>
@if(page.getUsers().isEmpty())
    <p>There are no users at the moment</p>
@else
    @for(var user : page.getUsers())
        <div>
            <p>${user.getName()}</p>
        </div>
    @endfor
@endif
</body>
</html>
