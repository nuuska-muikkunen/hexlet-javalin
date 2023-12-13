@import org.example.hexlet.util.NamedRoutes

@template.layout.page(
content = @`
    <form action="${NamedRoutes.carsPath()}" method="post">
        <div class="mb-3">
            <label class="form-label">
                Make
                <input type="text" class="form-control" name="make" />
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Model
                <input type="text" required class="form-control" name="model" />
            </label>
        </div>
        <input type="submit" value="Register" />
    </form>
`
)
