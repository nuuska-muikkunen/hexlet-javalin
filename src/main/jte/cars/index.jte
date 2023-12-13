@import org.example.hexlet.dto.cars.CarsPage
@import org.example.hexlet.util.NamedRoutes

@param CarsPage page

@template.layout.page(
content = @`
    <h1>Cars</h1>
    <table class="table table-striped">
        @for(var car : page.getCars())
            <tr>
                <td>
                    <a href="${NamedRoutes.carPath(car.getId())}">${car.getId()}</a>
                </td>
                <td>
                    ${car.getName()}
                </td>
                <td>
                    ${car.getModel()}
                </td>
                <td>
                    ${car.getModel()}
                </td>
            </tr>
        @endfor
    </table>
`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)
