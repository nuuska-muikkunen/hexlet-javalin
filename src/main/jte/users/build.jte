@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.UserPage

@param UserPage page

@template.layout.page(
content = @`
<%--    @if(page.getErrors() != null)--%>
<%--        <ul>--%>
<%--            @for(var validator : page.getErrors().values())--%>
<%--                @for(var error : validator)--%>
<%--                    <li>${error.getMessage()}</li>--%>
<%--                @endfor--%>
<%--            @endfor--%>
<%--        </ul>--%>
<%--    @endif--%>

    <form action="${NamedRoutes.usersPath()}" method="post">
        <div class="mb-3">
            <label class="form-label">
                Name
                <input type="text" class="form-control" name="name" />
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Email
                <input type="email" required class="form-control" name="email" />
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Password
                <input type="password" required class="form-control" name="password" />
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Password confirmation
                <input type="password" required class="form-control" name="passwordConfirmation" />
            </label>
        </div>
        <input type="submit" value="Register" />
    </form>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)