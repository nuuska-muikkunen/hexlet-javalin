@import org.example.hexlet.dto.users.UserPage

@param UserPage page

@template.layout.page(page = page, content = @`
    <h1>${page.getHeader()}</h1>
    <p>
        ${page.getUser().getId()}
        ${page.getUser().getName()}
    </p>
`)
