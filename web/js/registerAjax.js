
$(document).ready(function () {
    $("#errorInp").hide();
    $('#register-btn').click(function () {
        let url;
        if(location.href.includes("/cinema")){
            url = location.origin + "/cinema/shows_you/registration" + window.location.search;
        } else {
            url = location.origin + "/shows_you/registration" + window.location.search;
        }

        $.ajax({
            url: url,
            method: "post",
            data: {
                username : $('#username').val(),
                email : $('#email').val(),
                password : $('#password').val(),
                confirmPassword : $('#confirm_password').val()
            },
            error: function(message) {
                $("#errorInp").fadeTo(7000, 100).slideUp(700, function(){
                    $("#errorInp").slideUp(500);
                });
                let sp1 = document.createElement("span");
                sp1.setAttribute("id", "osp");
                let sp1_content = document.createTextNode(message.responseText);
                sp1.appendChild(sp1_content);
                let sp2 = document.getElementById("osp");
                let parentDiv = sp2.parentNode;
                parentDiv.replaceChild(sp1, sp2);
            },
            success: function(data) {
                window.open(data, "_self");
            }
        });
    })
});

