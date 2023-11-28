@template.layout.page(
content = @`<form action="/courses" method="post">
    <div>
        <label>
            Name
            <input type="text" name="name" />
        </label>
    </div>
    <div>
        <label>
            Description
            <input type="text" required name="description" />
        </label>
    </div>
    <input type="submit" value="Register" />
</form>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)