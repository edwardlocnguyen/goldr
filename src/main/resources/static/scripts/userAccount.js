/*
$(document).ready(function() {
  var userId = window.location.pathname.split("/")[2];

    // Get the accounts and put them in the table
    $.get("/users/" + userId + "/api/accounts", function(data) {
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
            var row = "<tr><td>" + account.name + "</td><td><button class='btn btn-danger btn-sm' onclick='deleteAccount(" + account.id + ")'>Delete</button></td></tr>";
            $("#accounts-list-table-body").append(row);
        }
    });

});

function deleteAccount(accountId) {
    var userId_str = window.location.pathname.split("/")[2];
    var userId = parseInt(userId_str);
    console.log("userId: ", userId);
    
    console.log("accountId: ", accountId);
    var accountId_int = parseInt(accountId);
    console.log("accountId_int: ", accountId_int);
    
	$.ajax({
	    url: "/users/" + userId + "/accounts/" + accountId_int + "?userId=" + userId,
	    type: "DELETE",
	    success: function() {
	        window.location.reload();
	    },
	    error: function() {
	        alert("Error deleting account");
	    }
	});

}
