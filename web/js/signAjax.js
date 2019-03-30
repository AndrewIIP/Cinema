
$(document).ready(function () {
    $("#errorInp").hide();
    $('#signBtn').click(function () {
        $.ajax({
            url: location.protocol + '//' + location.host + location.pathname + "login" +
                 window.location.search,
            method: "post",
            data: {
                username : $('#username').val(),
                password : $('#password').val()
            },
            error: function(message) {
                $("#errorInp").fadeTo(4000, 30).slideUp(700, function(){
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
                alert("SUCC " + data);
            }
        });
    })
});

