package security

class BootStrap {

    def init = { servletContext ->
        log.info("adding a user")
        User.withTransaction {
            new User(username: "admin", password: "pass").save(flush: true, failOnError: true)
        }
    }
    def destroy = {
    }
}
