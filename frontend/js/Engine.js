class Engine {

    constructor() {
        this.gameServerUrl = "http://localhost:8080"
    }

    load() {
        if ((this.getCookie("login") === undefined) || (this.getCookie("login") === null)) {
            window.location.href = "index_welcomePage.html";
        }
        document.getElementById("loginInfo").innerHTML = "Login: " + this.getCookie("login");
        if (!((this.getCookie("accounts") === undefined) || (this.getCookie("accounts") === null))) {
            document.getElementById("ListOfAccounts").innerHTML = this.getCookie("accounts");
        }
    };

    unload() {
        this.setCookie("login", "", {
            expires: -1
        });
        this.setCookie("accounts", "", {
            expires: -1
        });
        window.location.reload();
    };

    setCookie(name, value, options) {
        options = options || {};

        let expires = options.expires;

        if (typeof expires === "number" && expires) {
            let d = new Date();
            d.setTime(d.getTime() + expires * 1000);
            expires = options.expires = d;
        }
        if (expires && expires.toUTCString) {
            options.expires = expires.toUTCString();
        }

        value = encodeURIComponent(value);

        let updatedCookie = name + "=" + value;

        for (let propName in options) {
            updatedCookie += "; " + propName;
            let propValue = options[propName];
            if (propValue !== true) {
                updatedCookie += "=" + propValue;
            }
        }

        document.cookie = updatedCookie;
    };

    getCookie(name) {
        let matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    }

}

let newEngine = new Engine();