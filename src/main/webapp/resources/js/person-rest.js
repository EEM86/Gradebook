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
            url: "person/" + rowID,
            success: function (data, textStatus) {
                $("#editId").val(data.id);
                $("#editRoleId").val(data.roleId);
                $("#editFirstName").val(data.firstName);
                $("#editLastName").val(data.lastName);
                $("#editEmail").val(data.email);
                $("#editPhone").val(data.phone);
                $("#editAddress").val(data.address);
                $("#editBirthday").val(data.birthday);
                $("#editLogin").val(data.login);
                $("#editPassword").val(data.password);
                showEditForm();
            }
        })
    });
}

function deletePerson(x) {
    var rowID = x.parentElement.parentElement.cells[0].textContent;
    $(document).ready(function () {
        $.ajax({
            method: "DELETE",
            url: "person/" + rowID,
            success: function (data, textStatus) {
                window.location.reload(false);
            }

        })
    });
}

$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: "person/all",
        success: function (data, textStatus) {
            $.each(data, function (i, val) {
                $("#tableD").append("<tr><td class=\"nr\">"+val.id + "</td><td>"+ val.roleId + "</td><td>" +val.firstName
                    + "<td>"+val.lastName + "</td><td>"+ val.email + "</td><td>" + val.phone + "</td>"
                    + "<td>" + val.address + "</td><td>"+ val.birthday + "</td><td>" + val.departmentId + "</td>"
                    + "<td>" + val.curatorId + "</td><td>"+ val.groupId + "</td><td>" + val.login + "</td>"
                    + "<td>" + val.password + "</td><td><button type=\"button\" class=\"mybutton\" onclick=\"edit(this)\">Edit</button></td> "
                    + "<td><button type=\"button\" class=\"mybutton\" onclick=\"deletePerson(this)\">Delete</button></td></tr>");
            })
        }
    })
});

function save() {
    myJson = ({
        roleId: $("#roleId").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        email: $("#email").val(),
        phone: $("#phone").val(),
        address: $("#address").val(),
        birthday: $("#birthday").val(),
        departmentId: $("#departmentId").val(),
        curatorId: $("#curatorId").val(),
        groupId: $("#groupId").val(),
        login: $("#login").val(),
        password: $("#password").val()
    });
    $.ajax({
        contentType: "application/json",
        method: "POST",
        url: "person/create",
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $("#createModalForm").modal("hide")
            window.location.reload(false);
        }
    });
}

function update() {
    var id =  $("#editId").val();
    myJson = ({
        roleId: $("#editRoleId").val(),
        firstName: $("#editFirstName").val(),
        lastName: $("#editLastName").val(),
        email: $("#editEmail").val(),
        phone: $("#editPhone").val(),
        address: $("#editAddress").val(),
        birthday: $("#editBirthday").val(),
        departmentId: $("#editDepartmentId").val(),
        curatorId: $("#editCuratorId").val(),
        groupId: $("#editGroupId").val(),
        login: $("#editLogin").val(),
        password: $("#editPassword").val()
    });
    $.ajax({
        contentType: "application/json",
        method: "PUT",
        url: "person/" + id,
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $("#editModalForm").modal("hide")
            window.location.reload(false);
        }
    });
}
