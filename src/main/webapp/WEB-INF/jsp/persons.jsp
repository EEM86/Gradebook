<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Persons</title>
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

<div align="center"><jsp:include page="welcome.jsp"/></div>
<br>
<br>
<br>
<br>

<c:if test="${!empty getPersons}">
    <table class="tg" width="100%">
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
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getPersons}" var="person">
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
                <td><a href="<c:url value='/edit/${person.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/delete/${person.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="index.jsp">Back to main menu</a>
<br/>

<h1>Add a Person</h1>

<c:url var="addAction" value="/persons/add"/>

<form:form action="${addAction}" modelAttribute="person">
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
                <form:input path="roleId"/>
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
                <form:input path="email"/>
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
                <form:input path="birthday"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="departmentId">
                    <spring:message text="DepartID"/>
                </form:label>
            </td>
            <td>
                <form:input path="departmentId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="curatorId">
                    <spring:message text="CuratorId"/>
                </form:label>
            </td>
            <td>
                <form:input path="curatorId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="groupId">
                    <spring:message text="GroupId"/>
                </form:label>
            </td>
            <td>
                <form:input path="groupId"/>
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
                <c:if test="${!empty person.lastName}">
                    <input type="submit"
                           value="<spring:message text="Edit Person"/>"/>
                </c:if>
                <c:if test="${empty person.lastName}">
                    <input type="submit"
                           value="<spring:message text="Add Person"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
