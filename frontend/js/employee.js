getAllEmployee();

function saveEmployee() {

    let name = $('#exampleFormControlInput2').val();
    let address = $('#exampleFormControlInput3').val();
    let mNumber = $('#exampleFormControlInput4').val();

    /* console.log(name);
     console.log(address);
     console.log(mNumber );*/
    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/saveEmployee",
        Credential: false,
        async: true,
        data: JSON.stringify({ //stringify
            "empID": "",
            "empName": name,
            "empAddress": address,
            "empNumber": mNumber
        }),
        success: function(data) {
            alert("saved")
            getAllEmployee()
        },
        error: function(xhr, exception) {
            alert("Error")

        }
    })
}

function updateEmployee() {
    let id = $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let address = $('#exampleFormControlInput3').val();
    let mNumber = $('#exampleFormControlInput4').val();

    /* console.log(name);
     console.log(address);
     console.log(mNumber );*/
    $.ajax({
        method: "PUT",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/updateEmployee",
        Credential: false,
        async: true,
        data: JSON.stringify({ //stringify
            "empID": id,
            "empName": name,
            "empAddress": address,
            "empNumber": mNumber
        }),
        success: function(data) {
            alert("updated")
            getAllEmployee()
        },
        error: function(xhr, exception) {
            alert("Error")

        }
    })
}

function deleteEmployee() {
    let id = $('#exampleFormControlInput1').val();

    /* console.log(name);
     console.log(address);
     console.log(mNumber );*/
    $.ajax({
        method: "DELETE",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/deleteEmployee/" + id,
        Credential: false,
        async: true,
        success: function(data) {
            alert("deleted")
            getAllEmployee();
        },
        error: function(xhr, exception) {
            alert("Error")

        }
    })
}

function getAllEmployee() {

    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/getAllEmployee",
        Credential: false,
        async: true,
        success: function(data) {
            if (data.code === "00") {
                $('#empTable').empty();
                for (let emp of data.content) {
                    let id = emp.empID;
                    let name = emp.empName;
                    let address = emp.empAddress;
                    let number = emp.empNumber;

                    //var row = `<tr><td>${id}</td></tr><tr><td>${name}</td></tr><tr><td>${address}</td></tr><tr><td>${number}</td></tr>`;

                    var row = `
                    <tr>
                        <td>${id}</td>
                        <td>${name}</td>
                        <td>${address}</td>
                        <td>${number}</td>
                    </tr>`;

                    $('#empTable').append(row);

                }

            }

        },
        error: function(xhr, exception) {
            alert("Error")

        }
    })
}
$(document).ready(function() {
    // Add a click event listener to table rows
    $('#empTable').on('click', 'tr', function() {
        // Get the data from the clicked row
        var empID = $(this).find('td:eq(0)').text();
        var name = $(this).find('td:eq(1)').text();
        var address = $(this).find('td:eq(2)').text();
        var mNumber = $(this).find('td:eq(3)').text();

        // Populate the input boxes with the row's data
        $('#exampleFormControlInput1').val(empID);
        $('#exampleFormControlInput2').val(name);
        $('#exampleFormControlInput3').val(address);
        $('#exampleFormControlInput4').val(mNumber);
    });
});