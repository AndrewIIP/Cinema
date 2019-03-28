
$(document).ready(function () {
    $('#signBtn').click(function () {
        if($("#login-nav")[0].checkValidity()){
            alert("OK")
        }else{
            alert("BAD")
        }

        // TODO : make normal servlet url concatination
        let url = "http://localhost:8080/signin";
        let username = $('#username').val();
        let password = $('#password').val();

        $.ajax({
            url: url,
            method: "post",
            data: {
                username : username,
                password : password
            },
            error: function(message) {
                console.log(message);
            },
            success: function(data) {
                console.log("Success");
            }
        });
    })
});




