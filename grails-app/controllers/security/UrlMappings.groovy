package security

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "/api/index"(controller: "thing", action: "index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
