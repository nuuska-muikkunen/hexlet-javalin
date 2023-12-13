@import org.example.hexlet.dto.cars.CarPage

@param CarPage page

@template.layout.page(content = @`
    <h1>Car</h1>
    <h2>${page.getCar().getId()}</h2>
    <div>${page.getCar().getMake()}</div>
    <div>${page.getCar().getModel()}</div>
`
)
