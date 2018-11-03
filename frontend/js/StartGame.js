class StartGame {

    constructor(login) {
        this.gameServerUrl = "localhost:8080";
        this.login = login;
        this.gameId = null;
        this.handler = {};
        this.socket = null;
        this.handler['REPLICA'] = null;
        this.handler['POSSESS'] = null;
    };

    getSessionIdFromServer() {
        let that = this;
        if (!that.login) {
            alert("Please input login");
            console.log("Empty login, retry login");
        }
        $.ajax({
            type: 'POST',
            url: "http://" + that.gameServerUrl + "/join",
            contentType: 'application/x-www-form-urlencoded',
            data: {
                "login": that.login
            },
            dataType: 'text',
            success: function (data) {
                that.gameId = data;
                console.log("Matchmaker returned gameId=" + data);
                that.connectToGameServer(that.gameId, that.login);
            },
            error: function () {
                alert("GameId request failed");
                console.log("GameId request failed");
            }
        });
    };


    connectToGameServer(gameId, login) {
        let self = this;
        this.socket = new WebSocket("ws://" + this.gameServerUrl + "/game/connect?gameId=" + gameId + "&login=" + login);

        this.socket.onopen = function () {
            console.log("Connection established.");
        };

        this.socket.onclose = function (event) {
            if (event.wasClean) {
                console.log('closed');
            } else {
                console.log('alert close');
            }
            console.log('Code: ' + event.code + ' cause: ' + event.reason);
            window.location.reload(true);
        };

        this.socket.onmessage = function (event) {
            var msg = JSON.parse(event.data);
            if (self.handler[msg.topic] === undefined)
                return;

            self.handler[msg.topic](msg);
        };

        this.socket.onerror = function (error) {
            console.log("Error " + error.message);
        };
    }

}