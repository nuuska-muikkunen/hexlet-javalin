@import org.example.hexlet.dto.users.UsersPage

@param UsersPage page

@template.layout.page(page = page, content = @`
<h2>${page.getHeader()}</h2>
@if(page.getUsers().isEmpty())
    <p>There are no users at the moment</p>
@else
    @for(var user : page.getUsers())
        <div>
            <p>${user.getName()}</p>
        </div>
    @endfor
@endif
`)
