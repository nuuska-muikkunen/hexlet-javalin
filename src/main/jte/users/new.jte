@template.layout.page(
content = @`<form action="/users" method="post">
    <div>
        <label>
            Name
            <input type="text" name="name" />
        </label>
    </div>
    <div>
        <label>
            Email
            <input type="email" required name="email" />
        </label>
    </div>
    <div>
        <label>
            Password
            <input type="password" required name="password" />
        </label>
    </div>
    <div>
        <label>
            Password confirmation
            <input type="password" required name="passwordConfirmation" />
        </label>
    </div>
    <input type="submit" value="Register" />
</form>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)