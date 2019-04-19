function notify_tick(movie, day, time, place){
    let movHtml = document.getElementById("mov").innerText = movie;
    let dayHtml = document.getElementById("day").innerText = day;
    let timeHtml = document.getElementById("time").innerText = time;
    let placeHtml = document.getElementById("place").innerText = place;
    appearDivById("order-form");
}

function cls_span(id) {
    // document.getElementById("order-form").style.display = 'none';
    $('#' + id.toString()).slideUp(200);
}

function appearDivById(id) {
    if(document.getElementById(id).style.display === 'none'){
        $('#' + id.toString()).fadeTo(1200, 30);
    }
}