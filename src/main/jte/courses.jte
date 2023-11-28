@import org.example.hexlet.dto.courses.CoursePage

@param CoursePage page

@template.layout.page(
content = @`
    <h1>Курсы</h1>
    <table class="table table-striped">
        @for(var course : page.getCourses())
            <tr>
                <td>
                    <a href="/courses/${course.getId()}">${course.getId()}</a>
                </td>
                <td>
                    ${course.getName()}
                </td>
                <td>
                    <p>${course.getDescription()}</p>
                </td>
            </tr>
        @endfor
    </table>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
