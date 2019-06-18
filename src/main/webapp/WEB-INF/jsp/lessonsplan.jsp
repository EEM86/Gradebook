<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Lessons plans</title>

</head>
<body>
<div><jsp:include page="/"/></div>
<section>
    <c:if test="${!empty getLessonsplan}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="80">Discipline Name</th>
                <th width="80">Teacher Name</th>
                <th width="80">Group</th>
                <th width="40">Hours</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${getLessonsplan}" var="lessonsplan">
                <tr>
                    <td>${lessonsplan.id}</td>
                    <td>${lessonsplan.discipline.discName}</td>
                    <td>${lessonsplan.teacher.firstName} ${lessonsplan.teacher.lastName}</td>
                    <td>${lessonsplan.group.name}</td>
                    <td>${lessonsplan.hours}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="<c:url value='/lessonsplan/edit/${lessonsplan.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/lessonsplan/delete/${lessonsplan.id}'/>">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h1>Add lessons plan</h1>
        <c:url var="addAction" value="/lessonsplan/add"/>
        <form:form action="${addAction}" modelAttribute="lessonsplan">
            <table>
                <c:if test="${!empty lessonsplan.discipline.id}">
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
                        <form:label value="Discipline" path="discipline.discName">
                            <spring:message text="Discipline"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="discipline.discName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="teacher.firstName">
                            <spring:message text="Teacher Name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="teacher.firstName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="teacher.lastName">
                            <spring:message text="Teacher Surname"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="teacher.lastName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="group.name">
                            <spring:message text="Group"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="group.name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="hours">
                            <spring:message text="Hours"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="number" min="1" path="hours"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty lessonsplan.discipline.id}">
                            <input type="submit"
                                   value="<spring:message text="Edit lessons plan"/>"/>
                        </c:if>
                        <c:if test="${empty lessonsplan.discipline.id}">
                            <input type="submit"
                                   value="<spring:message text="Add lessons plan"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </sec:authorize>
</section>

</body>
</html>
