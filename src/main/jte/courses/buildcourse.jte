@import org.example.hexlet.dto.courses.BuildCoursePage
@import org.example.hexlet.util.NamedRoutes

@param BuildCoursePage page

@template.layout.page(
content = @`
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="${NamedRoutes.coursesPath()}" method="post">
        <div class="mb-3">
            <label class="form-label">
                Name
                <input type="text" class="form-control" name="name" value="${page.getName()}" />
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Description
                <input type="text" required class="form-control" name="description" value="${page.getDescription()}" />
            </label>
        </div>
        <input type="submit" value="Register" />
    </form>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)