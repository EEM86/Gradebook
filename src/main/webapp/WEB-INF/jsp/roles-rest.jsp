<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script src="<c:url value="/resources/js/role-rest.js" />"></script>
    <title>Role Rest API Page</title>
</head>

<body>

<div class="logout">
    <a href="${pageContext.request.contextPath}/logout"..>Sign Out</a>
</div>

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
                    <input type="number" min="1" class="form-control edt-field" id="editRoleId" placeholder="editRoleId">
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

<a href="/Gradebook/">Back to main menu</a>

</body>
</html>
