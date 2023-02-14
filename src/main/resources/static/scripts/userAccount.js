$(document).ready(function() {
    var userId = window.location.pathname.split("/")[2];
    $.get("/users/" + userId + "/api/accounts-amounts", function(data) {
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var row = "<tr><td>" + (i + 1) + "</td><td>" + account[1] + "</td><td>" + account[2] + "</td><td>" + account[3] + "</td></tr>";
            $("#accounts-table-body").append(row);
        }
    });
});
