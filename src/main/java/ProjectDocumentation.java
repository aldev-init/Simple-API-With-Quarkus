import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Course Service",
                version = "1.0",
                contact = @Contact(
                        name = "Muhammad Alghifari",
                        url = "https://aldev-init.github.io/",
                        email = "muhammad.alghifari@barrans.id"
                ),
                license = @License(
                        name = "aldev-resource 3.0",
                        url = "this Web"
                )

        )
)
public class ProjectDocumentation extends Application {
}
