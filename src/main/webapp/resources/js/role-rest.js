function showCreateForm() {
    $("#createModalForm").modal("show");
}

function showEditForm() {
    $("#editModalForm").modal("show");
}

function edit(x) {
    var rowID = x.parentElement.parentElement.cells[0].textContent;
    $(document).ready(function () {
        $.ajax({
            method: "GET",
            url: "role/" + rowID,
            success: function (data, textStatus) {
                $("#editRoleId").val(data.id),
                    $("#editRoleName").val(data.roleName);
                showEditForm();
            }
        })
    });
}

function deleteRole(x) {
    var rowID = x.parentElement.parentElement.cells[0].textContent;
    $(document).ready(function () {
        $.ajax({
            method: "DELETE",
            url: "role/" + rowID,
            success: function (data, textStatus) {
                window.location.reload(false);
            }
        })
    });
}

$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: "role/all",
        success: function (data, textStatus) {
            $.each(data, function (i, val) {
                $("#tableRole").append("<tr><td class=\"nr\">"+val.id + "</td><td>"+ val.roleName
                    + "</td><td><button type=\"button\" class=\"mybutton\" onclick=\"edit(this)\">Edit</button> "
                    + "<td><button type=\"button\" class=\"mybutton\" onclick=\"deleteRole(this)\">Delete</button></td></tr>");
            })
        }
    })
});


function save() {
    myJson = ({
        roleName: $("#roleName").val(),
    });
    $.ajax({
        contentType: "application/json",
        method: "POST",
        url: "role/create",
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $("#createModalForm").modal("hide")
            window.location.reload(false);
        }
    });
}

function update() {
    var id =  $("#editRoleId").val();
    myJson = ({
        roleName: $("#editRoleName").val(),
    });
    $.ajax({
        contentType: "application/json",
        method: "PUT",
        url: "role/" + id,
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $("#editModalForm").modal("hide")
            window.location.reload(false);
        }
    });
}
