function notify_tick(movie, day, time, place){
    document.getElementById("mov").innerText = movie;
    document.getElementById("day").innerText = day;
    document.getElementById("time").innerText = time;
    document.getElementById("place").innerText = place;
    appearDivById("order-form");
}

function notify_cancel(ticket) {
    document.getElementById("tk-num-holder").innerText = ticket;
}

function notify_del(msg, holderId) {
    document.getElementById(holderId.toString()).innerText = msg;
}

function cls_span(id) {
    $('#' + id.toString()).slideUp(200);
}

function appearDivById(id) {
    if(document.getElementById(id).style.display === 'none'){
        $('#' + id.toString()).fadeTo(800, 30);
    }
}