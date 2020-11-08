// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-3.3.1.min
//= require bootstrap
//= require popper.min
//= require_self

let logarea = document.querySelector("#log")

function log(message) {
    logarea.value += message + "\n"
    logarea.scrollTop = logarea.scrollHeight
}

let bearerToken
let refreshToken


document.querySelector("#login").addEventListener("click", async function () {

    let username = document.querySelector("#username").value
    let password = document.querySelector("#pass").value


    console.log("username = ", username)
    console.log("password = ", password)

    let body = {username: username, password: password}
    console.log("body = ", body);

    log("fetching.. " + JSON.stringify(body))

    let response = await fetch('/api/login', {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    })

    if (response.status === 401) {
        log("Got a 401")
    }

    let userinfo = await response.json()

    log("userinfo == " + JSON.stringify(userinfo))

    bearerToken = userinfo.access_token
    refreshToken = userinfo.refresh_token

})


let buttons = document.querySelectorAll(".refresh")
buttons.forEach((button) => {


    button.addEventListener("click", async () => {

        let headers = {
            "Content-Type": "application/x-www-form-urlencoded",
        }

        if (button.classList.contains("with")) {
            log("Sending refresh WITH token")
            headers['Authorization'] = `Bearer ${bearerToken}`
            if (!bearerToken) {
                log("ERROR**  expecting to send with bearer token, but no bearer token exists")
                return
            }
        } else {
            log("Sending refresh WITHOUT token")
        }


        let response = await fetch("/oauth/access_token", {
            method: 'POST', // or 'PUT'
            headers: headers,
            body: `grant_type=refresh_token&refresh_token=${refreshToken}`
        })

        log("got a " + response.status)

        if (response.status == 200) {
            let body = await response.json()

            log("response = " + JSON.stringify(body))

            bearerToken = body.access_token
            log("replaced bearer token")
        } else {

        }
    })
})

document.querySelector("#fetch").addEventListener("click", async () => {

    let props = {}

    props.headers = props.headers || {}
    if (bearerToken) {
        props.headers['Authorization'] = `Bearer ${bearerToken}`
    }
    let result = await fetch("/thing", props)
    log("result = " + result.status)

    let thing = await result.json()
    log("thing = " + JSON.stringify(thing))

})
