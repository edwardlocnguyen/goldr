$(document).ready(function() {
  var userId = window.location.pathname.split("/")[2];

  // Get the accounts and amounts
  $.get("/users/" + userId + "/api/accounts-amounts", function(data) {
    var totalAmount = 0;
    for (var i = 0; i < data.length; i++) {
      var account = data[i];
      var amount = parseFloat(account[3]);
      totalAmount += amount;
      var row = "<tr><td>" + (i + 1) + "</td><td>" + account[1] + "</td><td>" + account[2] + "</td><td>$" + account[3] + "</td></tr>";
      $("#accounts-table-body").append(row);
    }

    // Add empty row
    var emptyRow = "<tr><td></td><td></td><td></td><td></td></tr>";
    $("#accounts-table-body").append(emptyRow);

    // Add total row
    var totalRow = "<tr><td></td><td>TOTAL</td><td></td><td>$" + totalAmount.toFixed(2) + "</td></tr>";
    $("#accounts-table-body").append(totalRow);

    $("#table_card_body_total_value").text("$" + totalAmount.toFixed(2));
  });

    // Get the accounts and put them in the table
    $.get("/users/" + userId + "/api/accounts", function(data) {
		console.log("data: ", data);
        for (var i = 0; i < data.length; i++) {
            var account = data[i];
			console.log("account: ", account);
			console.log("account.id: ", account.id);
            var row = "<tr><td>" + account.name + "</td><td><button class='btn btn-danger btn-sm' onclick='deleteAccount(" + account.id + ")'>Delete</button></td></tr>";
            $("#accounts-list-table-body").append(row);
        }
    });

    // Set the userId in the create Account form
    document.getElementById("userId").value = parseInt(userId);
    // Set the userIdTxn in the create Txn form
    // document.getElementById("userIdTxn").value = parseInt(userId);

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
