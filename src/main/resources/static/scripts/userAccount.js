$(document).ready(function() {
    var userId = window.location.pathname.split("/")[3];
    $.get("/users/api/" + userId + "/accounts-amounts", function(data) {
        var count = 1;
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var row = "<tr><td>" + count + "</td><td>" + account[1] + "</td><td>" + account[2] + "</td></tr>";
            $("#accounts-table tbody").append(row);
            count++;
        }
    });
});
