@import org.example.hexlet.dto.users.UsersPage

@param UsersPage page

@template.layout.page(content = @`
    @for(var user : page.getUsers())
        <div>
            ${user.getId()}
            ${user.getName()}
        </div>
    @endfor
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)

