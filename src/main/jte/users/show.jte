@import org.example.hexlet.dto.users.UserPage

@param UserPage page

@template.layout.page(content = @`
    <h1>Пользователь</h1>
    <h2>${page.getUser().getId()}</h2>
    <div>${page.getUser().getName()}</div>
    <div>${page.getUser().getEmail()}</div>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
