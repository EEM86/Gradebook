<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'}).val();
        });
    </script>
    <title>Profile</title>
</head>
<body>
<title>Profile</title>
<section>
        <table class="tg" width="100%" var="person">
            <tr>
                <th width="60">ID</th>
                <th width="60">roleId</th>
                <th width="120">FirstName</th>
                <th width="120">LastName</th>
                <th width="120">email</th>
                <th width="120">phone</th>
                <th width="120">address</th>
                <th width="120">birthday</th>
                <th width="60">departmentId</th>
                <th width="60">curatorId</th>
                <th width="60">groupId</th>
                <th width="120">login</th>
                <th width="120">password</th>
            </tr>
                <tr>
                    <td>${person.id}</td>
                    <td>${person.roleId}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td>${person.email}</td>
                    <td>${person.phone}</td>
                    <td>${person.address}</td>
                    <td>${person.birthday}</td>
                    <td>${person.departmentId}</td>
                    <td>${person.curatorId}</td>
                    <td>${person.groupId}</td>
                    <td>${person.login}</td>
                    <td>${person.password}</td>
                </tr>
        </table>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <h1>Edit a person</h1>

    <c:url var="editAction" value="/persons/add"/>

    <form:form action="${editAction}" modelAttribute="person">
        <table>
            <c:if test="${!empty person.lastName}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="roleId">
                        <spring:message text="RoleId"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="roleId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="firstName">
                        <spring:message text="FirstName"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="firstName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastName">
                        <spring:message text="LastName"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="lastName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="email">
                        <spring:message text="Email"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="email" path="email"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="phone">
                        <spring:message text="Phone"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="phone"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address">
                        <spring:message text="Address"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="address"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="birthday">
                        <spring:message text="Birthday"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="text" id="datepicker" path="birthday"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="departmentId">
                        <spring:message text="DepartID"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="departmentId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="curatorId">
                        <spring:message text="CuratorId"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="curatorId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="groupId">
                        <spring:message text="GroupId"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="groupId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="login">
                        <spring:message text="Login"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="login"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message text="Password"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Edit Person"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
</section>

</body>
</html>
