@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.util.NamedRoutes

@param UsersPage page

@template.layout.page(
page = page,
content = @`
    <h1>Пользователи</h1>
    <table class="table table-striped">
        @for(var user : page.getUsers())
            <tr>
                <td>
                    <a href="${NamedRoutes.userPath(user.getId())}">${user.getId()}</a>
                </td>
                <td>
                    ${user.getName()}
                </td>
                <td>
                    ${user.getEmail()}
                </td>
            </tr>
        @endfor
    </table>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
