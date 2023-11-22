@import org.example.hexlet.dto.users.UsersPage

@param UsersPage page

@template.layout.page(page = page, content = @`
    <a href="/users/build">New User</a>
    @for(var user : page.getUsers())
        <div>
            ${user.getName()}
        </div>
    @endfor
`)

