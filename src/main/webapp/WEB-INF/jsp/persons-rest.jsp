<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style-rest.css" />" >
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="<c:url value="/resources/js/person-rest.js" />"></script>

    <title>Person Rest API Page</title>
</head>

<body>

<div class="logout">
    <a href="${pageContext.request.contextPath}/logout"..>Sign Out</a>
</div>

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
                    <label for="role" class="col-sm-2 col-form-label">RoleID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="role" placeholder="Role Id">
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
                        <input type="number" min="1" class="form-control edt-field" id="departmentId"
                               placeholder="Department ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="curator" class="col-sm-2 col-form-label">Curator ID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="curator" placeholder="Curator ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="group" class="col-sm-2 col-form-label">Group ID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="group" placeholder="Group ID">
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
                        <input type="number" min="1" class="form-control edt-field" id="editId" placeholder="editId">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editRoleId" class="col-sm-2 col-form-label">RoleID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="editRoleId" placeholder="Role Id">
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
                        <input type="number" min="1" class="form-control edt-field" id="editDepartmentId"
                               placeholder="Department ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editCuratorId" class="col-sm-2 col-form-label">Curator ID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="editCuratorId" placeholder="Curator ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editGroupId" class="col-sm-2 col-form-label">Group ID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="editGroupId" placeholder="Group ID">
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
        <th width="80">role</th>
        <th width="120">FirstName</th>
        <th width="120">LastName</th>
        <th width="120">email</th>
        <th width="120">phone</th>
        <th width="120">address</th>
        <th width="120">birthday</th>
        <th width="120">departmentId</th>
        <th width="120">curator</th>
        <th width="120">group</th>
        <th width="120">login</th>
        <th width="120">password</th>
        <th width="80">Edit</th>
        <th width="80">Delete</th>
    </tr>
</table>

<a href="/Gradebook/">Back to main menu</a>

</body>
</html>
