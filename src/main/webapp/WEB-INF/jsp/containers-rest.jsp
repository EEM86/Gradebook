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
    <script src="<c:url value="/resources/js/container-rest.js" />"></script>
    <title>Container Rest API Page</title>
</head>

<body>
<div class="logout">
    <a href="${pageContext.request.contextPath}/logout"..>Sign Out</a>
</div>

<div id="ftp-addresses-grid-buttons" class="bootgrid-header container-fluid pull-left">
    <div class="row actionBar">
        <div class="col-sm-12"></div>
        <button type="button" class="btn btn-primary btn-margin pull-left" onclick="showCreateForm()">
            Create container
        </button>
    </div>
</div>

<div class="modal fade in" id="createModalForm">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Create Container</h4>
                <button type="button" class="close" data-dismiss="modal">x</button>
            </div>
            <div class="modal-body">
                <div class="form-group row">
                    <label for="parentId" class="col-sm-2 col-form-label">ParentID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="parentId" placeholder="Parent ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="name" placeholder="Name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="chief" class="col-sm-2 col-form-label">Chief ID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="chief" placeholder="Chief ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="typeId" class="col-sm-2 col-form-label">Type ID</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="typeId" placeholder="Type ID">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="institutionCity" class="col-sm-2 col-form-label">Institution City</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="institutionCity" placeholder="Institution City">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="institutionAddress" class="col-sm-2 col-form-label">Institution Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="institutionAddress" placeholder="Institution Address">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-10">
                        <input type="tel" class="form-control edt-field" id="phone" placeholder="Phone">
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
                <h4 class="modal-title">Edit Container</h4>
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
                    <label for="editParentId" class="col-sm-2 col-form-label">ParentId</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="editParentId" placeholder="Parent Id">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editName" class="col-sm-2 col-form-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editName" placeholder="Name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editChiefId" class="col-sm-2 col-form-label">Chief Id</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="editChiefId" placeholder="Chief Id">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editTypeId" class="col-sm-2 col-form-label">Type Id</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control edt-field" id="editTypeId" placeholder="Type Id">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editInstitutionCity" class="col-sm-2 col-form-label">Institution City</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editInstitutionCity" placeholder="Institution City">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editInstitutionAddress" class="col-sm-2 col-form-label">Institution Address</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control edt-field" id="editInstitutionAddress" placeholder="Institution Address">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="editPhone" class="col-sm-2 col-form-label">Phone</label>
                    <div class="col-sm-10">
                        <input type="tel" class="form-control edt-field" id="editPhone" placeholder="Phone">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="editBtn-submit" onclick="update()" class="btn btn-primary">Update</button>
            </div>
        </div>
    </div>
</div>

<table class="tg" width="100%" id="tableC">
    <tr>
        <th width="80">ID</th>
        <th width="80">ParentId</th>
        <th width="120">Name</th>
        <th width="80">ChiefId</th>
        <th width="80">TypeId</th>
        <th width="120">InstitutionCity</th>
        <th width="120">InstitutionAddress</th>
        <th width="100">phone</th>
        <th width="80">Edit</th>
        <th width="80">Delete</th>
    </tr>
</table>

<a href="/Gradebook/">Back to main menu</a>

</body>
</html>
