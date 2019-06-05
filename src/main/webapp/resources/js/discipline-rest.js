    function edit(x) {
        var rowID = x.parentElement.parentElement.cells[0].textContent;
        $(document).ready(function () {
            $.ajax({
                method: "GET",
                url: "discipline/" + rowID,
                success: function (data, textStatus) {
                    $('#editDiscId').val(data.id),
                        $('#editDiscName').val(data.discName);
                    showEditForm();
                }
            })
        });
    }

function deleteDiscipline(x) {
    var rowID = x.parentElement.parentElement.cells[0].textContent;
    $(document).ready(function () {
        $.ajax({
            method: "DELETE",
            url: "discipline/" + rowID,
            success: function (data, textStatus) {
                window.location.reload(false);
            }
        })
    });
}

$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: "discipline/all",
        success: function (data, textStatus) {
            $.each(data, function (i, val) {
                $('#tableDisc').append("<tr><td class=\"nr\">"+val.id + "</td><td>"+ val.discName
                    + "</td><td><button type=\"button\" class=\"mybutton\" onclick=\"edit(this)\">Edit</button> "
                    + "<td><button type=\"button\" class=\"mybutton\" onclick=\"deleteDiscipline(this)\">Delete</button></td></tr>");
            })
        }
    })
});

function showCreateForm() {
    $('#createModalForm').modal("show");
};

function showEditForm() {
    $('#editModalForm').modal("show");
};

function save() {
    myJson = ({
        discName: $('#discName').val(),
    });
    $.ajax({
        contentType: "application/json",
        method: "POST",
        url: "discipline/create",
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $('#createModalForm').modal('hide')
            window.location.reload(false);
        }
    });
};

function update() {
    var id =  $('#editDiscId').val();
    myJson = ({
        discName: $('#editDiscName').val(),
    });
    $.ajax({
        contentType: "application/json",
        method: "PUT",
        url: "discipline/" + id,
        data: JSON.stringify(myJson),
        success: function (data, textStatus, xhr) {
            $('#editModalForm').modal('hide')
            window.location.reload(false);
        }
    });
};
