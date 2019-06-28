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
                url: "container/" + rowID,
                success: function (data, textStatus) {
                    $("#editId").val(data.id);
                    $("#editParentId").val(data.parentId);
                    $("#editName").val(data.name);
                    $("#editChiefId").val(data.chiefId);
                    $("#editTypeId").val(data.typeId);
                    $("#editInstitutionCity").val(data.institutionCity);
                    $("#editInstitutionAddress").val(data.institutionAddress);
                    $("#editPhone").val(data.phone);
                    showEditForm();
                }
            })
        });
    }

function deleteContainer(x) {
    var rowID = x.parentElement.parentElement.cells[0].textContent;
    $(document).ready(function () {
        $.ajax({
            method: "DELETE",
            url: "container/" + rowID,
            success: function (data, textStatus) {
                window.location.reload(false);
            }

        })
    });
}

$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: "container/all",
        success: function (data, textStatus) {
            $.each(data, function (i, val) {
                $("#tableC").append("<tr><td class=\"nr\">"+val.id + "</td><td>"+ val.parentId + "</td><td>" +val.name
                    + "<td>"+val.chiefId + "</td><td>"+ val.typeId + "</td><td>" + val.institutionCity + "</td>"
                    + "<td>" + val.institutionAddress + "</td><td>"+ val.phone
                    + "</td><td><button type=\"button\" class=\"mybutton\" onclick=\"edit(this)\">Edit</button> "
                    + "<td><button type=\"button\" class=\"mybutton\" onclick=\"deleteContainer(this)\">Delete</button></td></tr>");
            })
        }
    })
});

function save() {
    myJson = ({
        parentId: $("#parentId").val(),
        name: $("#name").val(),
        chiefId: $("#chiefId").val(),
        typeId: $("#typeId").val(),
        institutionCity: $("#institutionCity").val(),
        institutionAddress: $("#institutionAddress").val(),
        phone: $("#phone").val(),
    });
    $.ajax({
        contentType: "application/json",
        method: "POST",
        url: "container/create",
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
        parentId: $("#editParentId").val(),
        name: $("#editName").val(),
        chiefId: $("#editChiefId").val(),
        typeId: $("#editTypeId").val(),
        institutionCity: $("#editInstitutionCity").val(),
        institutionAddress: $("#editInstitutionAddress").val(),
        phone: $("#editPhone").val(),
    });
    $.ajax({
        contentType: "application/json",
        method: "PUT",
        url: "container/" + id,
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $("#editModalForm").modal("hide")
            window.location.reload(false);
        }
    });
}
