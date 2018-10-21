$('#loginForm').submit(function (e) {

    let login = document.getElementById("loginInputLogin").value;
    let password = document.getElementById("loginInputPassword").value;
    $.ajax({
        contentType: 'application/x-www-form-urlencoded',
        data: {
            "login": login,
            "password": password
        },
        dataType: 'text',
        type: 'POST',
        url: newEngine.bankServerUrl + "/" + "login",
        success: function (data) {
            window.location.href = "test.html";
        },
        error: function (data) {
            console.log(data);
        }
    });
    e.preventDefault();
});