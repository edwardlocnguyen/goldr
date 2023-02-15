$(document).ready(function() {
    var userId = window.location.pathname.split("/")[2];
    $.get("/users/" + userId + "/api/assets-amounts", function(data) {
        var totalAmount = 0;
        for (var i = 0; i < data.length; i++) {
            var asset = data[i];
            var amount = parseFloat(asset[2]);
            totalAmount += amount;
            var row = "<tr><td>" + (i + 1) + "</td><td>" + asset[1] + "</td><td>" + asset[2] + "</td><td>$" + asset[2] + "</td></tr>";
            $("#table tbody").append(row);
        }
        var emptyRow = "<tr><td></td><td></td><td></td><td></td></tr>";
        $("#table tbody").append(emptyRow);
        var totalRow = "<tr><td></td><td>TOTAL</td><td>100.00%</td><td>$" + totalAmount.toFixed(2) + "</td></tr>";
        $("#table tbody").append(totalRow);

        // Calculate and append the percentages
        $("#table tbody tr").each(function(index) {
            var row = $(this);
            if (index >= 0 && index < $("#table tbody tr").length - 2) {
                var amount = parseFloat(row.find("td:nth-child(3)").text());
                var percentage = (amount / totalAmount * 100).toFixed(2);
                row.find("td:nth-child(3)").text(percentage + "%");
            }
        });
    });
});
