<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Grades journals</title>

</head>
<body>
<div>
    <jsp:include page="/"/>
</div>
<section>
    <c:if test="${!empty getJournals}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="80">Discipline Name</th>
                <th width="80">Student Name</th>
                <th width="40">Grade</th>
                <th width="80">Teacher</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${getJournals}" var="journal">
                <tr>
                    <td>${journal.id}</td>
                    <td>${journal.discName}</td>
                    <td>${journal.studentName}</td>
                    <td>${journal.grade}</td>
                    <td>${journal.teacherName}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="<c:url value='/journal/edit/${journal.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/journal/delete/${journal.id}'/>">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')">
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
                        <form:select name="select_discipline" size="1" path="discId">
                            <c:if test="${!empty journal.discId}">
                                <option name="discId" value="${journal.discId}">${journal.discName}</option>
                            </c:if>
                            <c:if test="${empty journal.discId}">
                                <c:forEach items="${getDisciplines}" var="discipline">
                                    <option value="${discipline.id}">${discipline.discName}</option>
                                </c:forEach>
                            </c:if>
                        </form:select>
                    </td>
                        <%--                                            <td>
                                                                        <form:label value="Discipline" path="discName">
                                                                            <spring:message text="Discipline"/>
                                                                        </form:label>
                                                                    </td>
                                                                    <td>
                                                                        <form:input type="text" path="discName"/>
                                                                    </td>--%>
                </tr>

                <tr>
                        <%--<td>
                            <form:label path="studentName">
                                <spring:message text="Student"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="text" path="studentName"/>
                        </td>--%>
                    <td>
                        <form:label path="personId">
                            <spring:message text="Student"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_student" size="1" path="personId">
                            <c:if test="${!empty journal.discId}">
                                <option name="personId" value="${journal.personId}">${journal.studentName}</option>
                            </c:if>
                            <c:if test="${empty journal.discId}">
                                <c:forEach items="${getStudents}" var="student">
                                    <option name="iddisc" value="${student.id}">${student.firstName} ${student.lastName}</option>
                                </c:forEach>
                            </c:if>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="grade">
                            <spring:message text="Grade"/>
                        </form:label>
                    </td>
                        <%--                    <td>
                                                <form:input type="number" min="1" path="grade"/>
                                            </td>--%>
                    <td>
                        <form:select name="select_grade" size="1" path="grade">
                            <c:if test="${!empty journal.discId}">
                                <option name="grade" value="${journal.grade}">${journal.grade}</option>
                            </c:if>
                            <c:if test="${empty journal.discId}">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </c:if>
                        </form:select>
                    </td>
                </tr>
                <tr>
                        <%--                    <td>
                                                <form:label path="teacherName">
                                                    <spring:message text="Teacher"/>
                                                </form:label>
                                            </td>
                                            <td>
                                                <sec:authorize access="hasRole('ROLE_TEACHER')">
                                                    <form:input type="text" path="teacherName"/>
                                                </sec:authorize>
                                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                    <form:input type="text" path="teacherName"/>
                                                </sec:authorize>
                                            </td>--%>
                    <td>
                        <form:label path="teacherId">
                            <spring:message text="Teacher"/>
                        </form:label>
                    </td>
                    <td>
                        <sec:authorize access="hasRole('ROLE_TEACHER')">
                            <%--                            <form:input type="number" value="${teacherName.firstName} ${teacherName.lastName}" min="1"
                                                                    path="teacherName" readonly="true" disabled="true"/>
                                                        <form:hidden path="teacherName"/>--%>
                            <form:select name="select_teacher" size="1" path="teacherId">
                                <option name="teacherName" value="${teacherName.id}">${teacherName.firstName} ${teacherName.lastName}</option>
                            </form:select>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <%--<form:input type="number" min="1" path="teacherId"/>--%>
                            <form:select name="select_teacher" size="1" path="teacherId">
                                <c:if test="${!empty journal.discId}">
                                    <option name="teacherId" value="${journal.teacherId}">${journal.teacherName}</option>
                                </c:if>
                                <c:if test="${empty journal.discId}">
                                    <c:forEach items="${getTeachers}" var="teacher">
                                        <option name="idteacher" value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                    </c:forEach>
                                </c:if>
                            </form:select>
                        </sec:authorize>
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
    </sec:authorize>
</section>

</body>
</html>
