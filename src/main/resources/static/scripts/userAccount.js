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

    // Get the accounts and put them in the unordered list
    $.get("/users/" + userId + "/api/accounts", function(data) {
        var listItems = '';
		for (var i = 0; i < data.length; i++) {
		    var account = data[i];
		    listItems += "<li>" + account.name + " <button class='btn btn-primary btn-sm' onclick='editAccount(" + account.id + ")'>Update</button> <button class='btn btn-danger btn-sm' onclick='deleteAccount(" + account.id + ")'>Delete</button></li>";
		}
		$('#asset_list').html(listItems);
    });
    
    // Set the userId in the create Asset form
    document.getElementById("userId").value = parseInt(userId);
});
