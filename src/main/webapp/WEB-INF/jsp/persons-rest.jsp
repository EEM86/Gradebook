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
    <%--<script src="/js/personRest.js"/>--%>
    <script type="text/javascript">

        function edit(x) {
            var rowID = x.parentElement.parentElement.cells[0].textContent;
            $(document).ready(function () {
                $.ajax({
                    method: "GET",
                    url: "person/" + rowID,
                    success: function (data, textStatus) {
                        $('#editId').val(data.id);
                        $('#editRoleId').val(data.roleId);
                        $('#editFirstName').val(data.firstName);
                        $('#editLastName').val(data.lastName);
                        $('#editEmail').val(data.email);
                        $('#editPhone').val(data.phone);
                        $('#editAddress').val(data.address);
                        $('#editBirthday').val(data.birthday);
                        $('#editLogin').val(data.login);
                        $('#editPassword').val(data.password);
                        showEditForm();
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
                        $('#tableD').append("<tr><td class=\"nr\">"+val.id + "</td><td>"+ val.roleId + "</td><td>" +val.firstName
                                + "<td>"+val.lastName + "</td><td>"+ val.email + "</td><td>" + val.phone + "</td>"
                            + "<td>" + val.address + "</td><td>"+ val.birthday + "</td><td>" + val.departmentId + "</td>"
                            + "<td>" + val.curatorId + "</td><td>"+ val.groupId + "</td><td>" + val.login + "</td>"
                            + "<td>" + val.password + "</td>  <td><button type=\"button\" class=\"mybutton\" onclick=\"edit(this)\">Edit</button> </td></tr> ");
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
                roleId: $('#roleId').val(),
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                email: $('#email').val(),
                phone: $('#phone').val(),
                address: $('#address').val(),
                birthday: $('#birthday').val(),
                departmentId: $('#departmentId').val(),
                curatorId: $('#curatorId').val(),
                groupId: $('#groupId').val(),
                login: $('#login').val(),
                password: $('#password').val()
            });
            $.ajax({
                contentType: "application/json",
                method: "POST",
                url: "person/create",
                data: JSON.stringify(myJson),
                success: function (data, textStatus, xhr) {
                    $('#createModalForm').modal('hide')
                }
            });
        };

        function update() {
            var id =  $('#editId').val();
            myJson = ({
                roleId: $('#editRoleId').val(),
                firstName: $('#editFirstName').val(),
                lastName: $('#editLastName').val(),
                email: $('#editEmail').val(),
                phone: $('#editPhone').val(),
                address: $('#editAddress').val(),
                birthday: $('#editBirthday').val(),
                departmentId: $('#editDepartmentId').val(),
                curatorId: $('#editCuratorId').val(),
                groupId: $('#editGroupId').val(),
                login: $('#editLogin').val(),
                password: $('#editPassword').val()
            });
            $.ajax({
                contentType: "application/json",
                method: "PUT",
                url: "person/" + id,
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

    <title>Person Rest API Page</title>
</head>

<body>

<div id="ftp-addresses-grid-buttons" class="bootgrid-header container-fluid pull-left">
    <div class="row actionBar">
        <div class="col-sm-12"></div>
        <button type="button" class="btn btn-primary btn-margin pull-left" onclick="showCreateForm()">
            Create person
        </button>
    </div>
</div>

<div class="modal fade in" id="createModalForm">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Create Person</h4>
                <button type="button" class="close" data-dismiss="modal">x</button>
            </div>
            <div class="modal-body">
                <div class="form-group row">
                    <label for="roleId" class="col-sm-2 col-form-label">RoleID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="roleId" placeholder="Role Id">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="firstName" class="col-sm-2 col-form-label">First name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="firstName" placeholder="First name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lastName" class="col-sm-2 col-form-label">Last name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="lastName" placeholder="Last name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="email" placeholder="Email">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-10">
                        <input type="tel" class="form-control edt-field" id="phone" placeholder="Phone">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="address" class="col-sm-2 col-form-label">Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="address" placeholder="Address">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="birthday" class="col-sm-2 col-form-label">Birthday</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control edt-field" id="birthday" placeholder="Birthday">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="departmentId" class="col-sm-2 col-form-label">Department ID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="departmentId"
                               placeholder="Department ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="curatorId" class="col-sm-2 col-form-label">Curator ID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="curatorId" placeholder="Curator ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="groupId" class="col-sm-2 col-form-label">Group ID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="groupId" placeholder="Group ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="login" class="col-sm-2 col-form-label">Login</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="login" placeholder="Login">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control edt-field" id="password" placeholder="Password">
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
                <h4 class="modal-title">Edit Person</h4>
                <button type="button" class="close" data-dismiss="modal">x</button>
            </div>
            <div class="modal-body">
                <div class="form-group row" style="display: none;">
                    <label for="editId" class="col-sm-2 col-form-label">editID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="editId" placeholder="editId">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editRoleId" class="col-sm-2 col-form-label">RoleID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="editRoleId" placeholder="Role Id">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editFirstName" class="col-sm-2 col-form-label">First name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editFirstName" placeholder="First name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editLastName" class="col-sm-2 col-form-label">Last name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editLastName" placeholder="Last name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editEmail" class="col-sm-2 col-form-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editEmail" placeholder="Email">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editPhone" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-10">
                        <input type="tel" class="form-control edt-field" id="editPhone" placeholder="Phone">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editAddress" class="col-sm-2 col-form-label">Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editAddress" placeholder="Address">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editBirthday" class="col-sm-2 col-form-label">Birthday</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control edt-field" id="editBirthday" placeholder="Birthday">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editDepartmentId" class="col-sm-2 col-form-label">Department ID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="editDepartmentId"
                               placeholder="Department ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editCuratorId" class="col-sm-2 col-form-label">Curator ID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="editCuratorId" placeholder="Curator ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editGroupId" class="col-sm-2 col-form-label">Group ID</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control edt-field" id="editGroupId" placeholder="Group ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editLogin" class="col-sm-2 col-form-label">Login</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editLogin" placeholder="Login">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editPassword" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control edt-field" id="editPassword" placeholder="Password">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="editBtn-submit" onclick="update()" class="btn btn-primary">Update</button>
            </div>
        </div>
    </div>
</div>

<table class="tg" width="100%" id="tableD">
    <tr>
        <th width="80">ID</th>
        <th width="80">roleId</th>
        <th width="120">FirstName</th>
        <th width="120">LastName</th>
        <th width="120">email</th>
        <th width="120">phone</th>
        <th width="120">address</th>
        <th width="120">birthday</th>
        <th width="120">departmentId</th>
        <th width="120">curatorId</th>
        <th width="120">groupId</th>
        <th width="120">login</th>
        <th width="120">password</th>
        <th width="80">Edit</th>
    </tr>
</table>

</body>
</html>