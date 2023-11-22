@import org.example.hexlet.dto.courses.CoursePage

@param CoursePage page

@template.layout.page(page = page, content = @`
<h2>${page.getHeader()}</h2>
@if(page.getCourses().isEmpty())
    <p>There are no courses at the moment</p>
@else
    @for(var course : page.getCourses())
        <div>
            <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
    @endfor
@endif
`)
