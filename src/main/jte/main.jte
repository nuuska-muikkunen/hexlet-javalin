@import org.example.hexlet.dto.MainPage;

@param MainPage page

@template.layout.page(
content = @`
    <body>
    <main>
        <h2>Welcome to Hexlet</h2>
        @if(!page.isVisited())
            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
    </main>
    </body>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
