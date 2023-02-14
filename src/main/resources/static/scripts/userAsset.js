$(document).ready(function() {
    var userId = window.location.pathname.split("/")[2];
    $.get("/users/" + userId + "/api/assets-amounts", function(data) {
		var count = 1;
        for (var i = 0; i < data.length; i++) {
            var asset = data[i];
            var row = "<tr><td>" + count + "</td><td>" + asset[1] + "</td><td>" + asset[2] + "</td></tr>";
            $("#assets-table tbody").append(row);
        }
    });
});
