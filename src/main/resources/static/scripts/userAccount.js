$(document).ready(function() {
    var userId = window.location.pathname.split("/")[2];

    // Get the accounts and amounts
    $.get("/users/" + userId + "/api/accounts-amounts", function(data) {
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var row = "<tr><td>" + (i + 1) + "</td><td>" + account[1] + "</td><td>" + account[2] + "</td><td>" + account[3] + "</td></tr>";
            $("#accounts-table-body").append(row);
        }
    });

    // Get the accounts and put them in the table
    $.get("/users/" + userId + "/api/accounts", function(data) {
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var row = "<tr><td>" + account.name + "</td><td><button class='btn btn-primary btn-sm' onclick='editAccount(" + account[0] + ")'>Update</button></td><td><button class='btn btn-danger btn-sm' onclick='deleteAccount(" + account[0] + ")'>Delete</button></td></tr>";
            $("#accounts-list-table-body").append(row);
        }
    });

    // Set the userId in the create Asset form
    document.getElementById("userId").value = parseInt(userId);
});
