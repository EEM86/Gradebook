<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Disciplines</title>
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

<c:if test="${!empty getDisciplines}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="80">discName</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getDisciplines}" var="discipline">
            <tr>
                <td>${discipline.id}</td>
                <td>${discipline.discName}</td>
                <td><a href="<c:url value='/disciplines/edit/${discipline.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/disciplines/delete/${discipline.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="index.jsp">Back to main menu</a>
<br/>

<h1>Add a Discipline</h1>

<c:url var="addAction" value="/disciplines/add"/>

<form:form action="${addAction}" modelAttribute="discipline">
    <table>
        <c:if test="${!empty discipline.discName}">
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
                <form:label path="discName">
                    <spring:message text="discName"/>
                </form:label>
            </td>
            <td>
                <form:input path="discName"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty discipline.discName}">
                    <input type="submit"
                           value="<spring:message text="Edit Discipline"/>"/>
                </c:if>
                <c:if test="${empty discipline.discName}">
                    <input type="submit"
                           value="<spring:message text="Add Discipline"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
