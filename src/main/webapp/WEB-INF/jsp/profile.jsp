<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <title>Profile</title>
</head>
<body>
<title>Profile</title>
<div>
    <jsp:include page="/"/>
</div>
<section>
        <table class="tg" width="100%" var="person">
            <tr>
                <th width="60">ID</th>
                <th width="60">role</th>
                <th width="120">FirstName</th>
                <th width="120">LastName</th>
                <th width="120">email</th>
                <th width="120">phone</th>
                <th width="120">address</th>
                <th width="120">birthday</th>
                <th width="60">department</th>
                <th width="60">curator</th>
                <th width="60">group</th>
                <th width="120">login</th>
                <th width="120">password</th>
            </tr>
                <tr>
                    <td>${person.id}</td>
                    <td>${person.role.roleName}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td>${person.email}</td>
                    <td>${person.phone}</td>
                    <td>${person.address}</td>
                    <td>${person.birthday}</td>
                    <td>${person.department.name}</td>
                    <td>${person.curator.firstName} ${person.curator.lastName}</td>
                    <td>${person.group.name}</td>
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
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="role.id">
                        <spring:message text="Role"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="role.roleName" readonly="true" disabled="true"/>
                    <form:hidden path="role.id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="firstName">
                        <spring:message text="First Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="firstName" readonly="true" disabled="true"/>
                    <form:hidden path="firstName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastName">
                        <spring:message text="Last Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="lastName" readonly="true" disabled="true"/>
                    <form:hidden path="lastName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="email">
                        <spring:message text="Email"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="email" readonly="true" disabled="true"/>
                    <form:hidden path="email"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="phone">
                        <spring:message text="Phone"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="phone" required="true" maxlength="15"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address">
                        <spring:message text="Address"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="address" readonly="true" disabled="true"/>
                    <form:hidden path="address"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="birthday">
                        <spring:message text="Birthday"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="birthday" readonly="true" disabled="true"/>
                    <form:hidden path="birthday"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="department.id">
                        <spring:message text="Department"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="department.name" readonly="true" disabled="true"/>
                    <form:hidden path="department.id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="curator.id">
                        <spring:message text="Curator"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="curator.id" value="${person.curator.firstName} ${person.curator.lastName}" readonly="true" disabled="true"/>
                    <form:hidden path="curator.id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="group.id">
                        <spring:message text="Group"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="group.name" readonly="true" disabled="true"/>
                    <form:hidden path="group.id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="login">
                        <spring:message text="Login"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="login" required="true" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message text="Password"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="password" required="true" maxlength="20"/>
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
