<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${getLessonsplan}" var="lessonsplan">
                <tr>
                    <td>${lessonsplan.id}</td>
                    <td>${lessonsplan.discId}</td>
                    <td>${lessonsplan.teacherId}</td>
                    <td>${lessonsplan.groupId}</td>
                    <td>${lessonsplan.hours}</td>
                    <td><a href="<c:url value='/lessonsplan/edit/${lessonsplan.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/lessonsplan/delete/${lessonsplan.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <h1>Add lessons plan</h1>
    <c:url var="addAction" value="/lessonsplan/add"/>
    <form:form action="${addAction}" modelAttribute="lessonsplan">
        <table>
            <c:if test="${!empty lessonsplan.discId}">
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
                    <form:label value="Discipline" path="discId">
                        <spring:message text="Discipline"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="discId"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="teacherId">
                        <spring:message text="Teacher"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="teacherId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="groupId">
                        <spring:message text="Group"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="groupId"/>
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
                    <c:if test="${!empty lessonsplan.discId}">
                        <input type="submit"
                               value="<spring:message text="Edit lessons plan"/>"/>
                    </c:if>
                    <c:if test="${empty lessonsplan.discId}">
                        <input type="submit"
                               value="<spring:message text="Add lessons plan"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</section>

</body>
</html>
