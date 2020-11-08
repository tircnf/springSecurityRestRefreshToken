package security

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

@Secured("permitAll")
class ThingController {

    def index() {
        render([hello: "world"] as JSON)
    }
}
