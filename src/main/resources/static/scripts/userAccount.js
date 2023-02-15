$(document).ready(function() {
    var userId = window.location.pathname.split("/")[2];

    // Get the accounts and amounts
    $.get("/users/" + userId + "/api/accounts-amounts", function(data) {
        var totalAmount = 0;
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var amount = parseInt(account[3]);
            totalAmount += amount;
            var row = "<tr><td>" + (i + 1) + "</td><td>" + account[1] + "</td><td>" + account[2] + "</td><td>$" + account[3] + "</td></tr>";
            $("#accounts-table-body").append(row);
        }
        $("#table_card_body_total_value").text("$" + totalAmount.toFixed(2));
    });

    // Get the accounts and put them in the table
    $.get("/users/" + userId + "/api/accounts", function(data) {
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var row = "<tr><td>" + account.name + "</td><td><button class='btn btn-danger btn-sm' onclick='deleteAccount(" + account[0] + ")'>Delete</button></td></tr>";
            $("#accounts-list-table-body").append(row);
        }
    });

    // Set the userId in the create Account form
    document.getElementById("userId").value = parseInt(userId);
    // Set the userIdTxn in the create Txn form
    document.getElementById("userIdTxn").value = parseInt(userId);
    // Set the userIdTxn in the delete Account button
    document.getElementById("userIdDel").value = parseInt(userId);
});
