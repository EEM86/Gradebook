<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html">
    <meta charset="utf=8">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Grades journals</title>

</head>
<body>
<div><jsp:include page="/"/></div>
<section>
    <c:if test="${!empty getJournals}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="80">Discipline Name</th>
                <th width="80">Student Name</th>
                <th width="40">Grade</th>
                <th width="80">Teacher</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${getJournals}" var="journal">
                <tr>
                    <td>${journal.id}</td>
                    <td>${journal.discId}</td>
                    <td>${journal.personId}</td>
                    <td>${journal.grade}</td>
                    <td>${journal.teacherId}</td>
                    <td><a href="<c:url value='/journal/edit/${journal.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/journal/delete/${journal.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <h1>Add a journal</h1>

    <c:url var="addAction" value="/journal/add"/>
    <form:form action="${addAction}" modelAttribute="journal">
        <table>
            <c:if test="${!empty journal.discId}">
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
                    <form:label path="personId">
                        <spring:message text="Student"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="personId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="grade">
                        <spring:message text="Grade"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="grade"/>
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
                <td colspan="2">
                    <c:if test="${!empty journal.discId}">
                        <input type="submit"
                               value="<spring:message text="Edit journal"/>"/>
                    </c:if>
                    <c:if test="${empty journal.discId}">
                        <input type="submit"
                               value="<spring:message text="Add journal"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</section>

</body>
</html>
