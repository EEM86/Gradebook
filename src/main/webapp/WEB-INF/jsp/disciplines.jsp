<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Disciplines</title>

</head>
<body>
<div><jsp:include page="/"/></div>
<section>
    <c:if test="${!empty getDisciplines}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="80">discName</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${getDisciplines}" var="discipline">
                <tr>
                    <td>${discipline.id}</td>
                    <td>${discipline.discName}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="<c:url value='/disciplines/edit/${discipline.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/disciplines/delete/${discipline.id}'/>">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
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
    </sec:authorize>
</section>

</body>
</html>
