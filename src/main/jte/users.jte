@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <h1>Пользователи</h1>
    <table class="table table-striped">
        @for(var user : page.getUsers())
            <tr>
                <td>
                    ${user.getId()}
                </td>
                <td>
                    <a href="/users/${user.getId()}">${user.getName()}</a>
                </td>
            </tr>
        @endfor
    </table>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
