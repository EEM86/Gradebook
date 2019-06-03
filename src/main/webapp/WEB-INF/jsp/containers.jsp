<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Container</title>
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

<c:if test="${!empty getContainers}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="80">Parent Id</th>
            <th width="120">Name</th>
            <th width="80">Chief Id</th>
            <th width="80">Type Id</th>
            <th width="120">Institution City</th>
            <th width="120">Institution Address</th>
            <th width="120">Phone</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getContainers}" var="container">
            <tr>
                <td>${container.id}</td>
                <td>${container.parentId}</td>
                <td>${container.name}</td>
                <td>${container.chiefId}</td>
                <td>${container.typeId}</td>
                <td>${container.institutionCity}</td>
                <td>${container.institutionAddress}</td>
                <td>${container.phone}</td>
                <td><a href="<c:url value='/containers/edit/${container.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/containers/delete/${container.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="index.jsp">Back to main menu</a>
<br/>

<h1>Add a Container</h1>

<c:url var="addAction" value="/containers/add"/>

<form:form action="${addAction}" modelAttribute="container">
    <table>
        <c:if test="${!empty container.name}">
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
                <form:label path="parentId">
                    <spring:message text="parentId"/>
                </form:label>
            </td>
            <td>
                <form:input path="parentId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="chiefId">
                    <spring:message text="chiefId"/>
                </form:label>
            </td>
            <td>
                <form:input path="chiefId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="typeId">
                    <spring:message text="typeId"/>
                </form:label>
            </td>
            <td>
                <form:input path="typeId"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="institutionCity">
                    <spring:message text="institutionCity"/>
                </form:label>
            </td>
            <td>
                <form:input path="institutionCity"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="institutionAddress">
                    <spring:message text="institutionAddress"/>
                </form:label>
            </td>
            <td>
                <form:input path="institutionAddress"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="phone">
                    <spring:message text="phone"/>
                </form:label>
            </td>
            <td>
                <form:input path="phone"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty container.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Container"/>"/>
                </c:if>
                <c:if test="${empty container.name}">
                    <input type="submit"
                           value="<spring:message text="Add Container"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
