@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.util.NamedRoutes

@param CoursesPage page

@template.layout.page(
content = @`
    <h1>Курс</h1>
    <table class="table table-striped">
        @for(var course : page.getCourses())
            <tr>
                <td>
                    ${course.getId()}
                </td>
                <td>
                    ${course.getName()}
                </td>
                <td>
                    ${course.getDescription()}
                </td>
            </tr>
        @endfor
    </table>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
