<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript">

        function edit(x) {
            var rowID = x.parentElement.parentElement.cells[0].textContent;
            $(document).ready(function () {
                $.ajax({
                    method: "GET",
                    url: "role/" + rowID,
                    success: function (data, textStatus) {
                        $('#editRoleId').val(data.id),
                        $('#editRoleName').val(data.roleName);
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
                        $('#tableRole').append("<tr><td class=\"nr\">"+val.id + "</td><td>"+ val.roleName
                            + "</td><td><button type=\"button\" class=\"mybutton\" onclick=\"edit(this)\">Edit</button> "
                            + "<td><button type=\"button\" class=\"mybutton\" onclick=\"deleteRole(this)\">Delete</button></td></tr>");
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
                roleName: $('#roleName').val(),
            });
            $.ajax({
                contentType: "application/json",
                method: "POST",
                url: "role/create",
                data: JSON.stringify(myJson),
                success: function (data, textStatus, xhr) {
                    $('#createModalForm').modal('hide')
                    window.location.reload(false);
                }
            });
        };

        function update() {
            var id =  $('#editRoleId').val();
            myJson = ({
                roleName: $('#editRoleName').val(),
            });
            $.ajax({
                contentType: "application/json",
                method: "PUT",
                url: "role/" + id,
                data: JSON.stringify(myJson),
                success: function (data, textStatus, xhr) {
                    $('#editModalForm').modal('hide')
                    window.location.reload(false);
                }
            });
        };
    </script>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

    <title>Role Rest API Page</title>
</head>

<body>

<div id="ftp-addresses-grid-buttons" class="bootgrid-header container-fluid pull-left">
    <div class="row actionBar">
        <div class="col-sm-12"></div>
        <button type="button" class="btn btn-primary btn-margin pull-left" onclick="showCreateForm()">
            Create role
        </button>
    </div>
</div>

<div class="modal fade in" id="createModalForm">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Create Role</h4>
                <button type="button" class="close" data-dismiss="modal">x</button>
            </div>
            <div class="modal-body">
                <div class="form-group row">
                    <label for="roleName" class="col-sm-2 col-form-label">RoleName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="roleName" placeholder="Role Name">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="btn-submit" onclick="save()" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade in" id="editModalForm">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Edit Role</h4>
                <button type="button" class="close" data-dismiss="modal">x</button>
            </div>
            <div class="modal-body">
                <div class="form-group row" style="display: none;">
                <label for="editRoleId" class="col-sm-2 col-form-label">editRoleId</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control edt-field" id="editRoleId" placeholder="editRoleId">
                </div>
                </div>
                <div class="form-group row">
                    <label for="editRoleName" class="col-sm-2 col-form-label">editRoleName</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editRoleName" placeholder="editRoleName">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="editBtn-submit" onclick="update()" class="btn btn-primary">Update</button>
            </div>
        </div>
    </div>
</div>

<table class="tg" id="tableRole">
    <tr>
        <th width="80">ID</th>
        <th width="80">roleName</th>
        <th width="80">Edit</th>
        <th width="80">Delete</th>
    </tr>
</table>

<a href="../Gradebook/index.jsp">Back to main menu</a>

</body>
</html>