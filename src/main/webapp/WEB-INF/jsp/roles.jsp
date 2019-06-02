<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Roles</title>
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

</head>
<body>

<c:if test="${!empty getRoles}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="80">roleName</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getRoles}" var="role">
            <tr>
                <td>${role.id}</td>
                <td>${role.roleName}</td>
                <td><a href="<c:url value='/roles/edit/${role.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/roles/delete/${role.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="index.jsp">Back to main menu</a>
<br/>

<h1>Add a Role</h1>

<c:url var="addAction" value="/roles/add"/>

<form:form action="${addAction}" modelAttribute="role">
    <table>
        <c:if test="${!empty role.roleName}">
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
                <form:label path="roleName">
                    <spring:message text="RoleName"/>
                </form:label>
            </td>
            <td>
                <form:input path="roleName"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty role.roleName}">
                    <input type="submit"
                           value="<spring:message text="Edit Role"/>"/>
                </c:if>
                <c:if test="${empty role.roleName}">
                    <input type="submit"
                           value="<spring:message text="Add Role"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
