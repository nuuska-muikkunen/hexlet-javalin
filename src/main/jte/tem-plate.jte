@import org.example.hexlet.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
content = @`
    <a href="/users">New User</a>
    @for(var user : page.getUsers())
        <div>
            ${user.getName()}
        </div>
    @endfor
`,
)

