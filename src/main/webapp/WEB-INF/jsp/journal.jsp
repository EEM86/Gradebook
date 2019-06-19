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
                    <td>${journal.discipline.discName}</td>
                    <td>${journal.student.firstName} ${journal.student.lastName}</td>
                    <td>${journal.grade}</td>
                    <td>${journal.teacher.firstName} ${journal.teacher.lastName}</td>
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
                <c:if test="${!empty journal.discipline.id}">
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
                        <form:label value="Discipline" path="discipline.id">
                            <spring:message text="Discipline"/>
                        </form:label>
                    </td>

                    <td>
                        <form:select name="select_discipline" size="1" path="discipline.id">
                            <c:forEach items="${getDisciplines}" var="discipline">
                                <option value="${discipline.id}"
                                        <c:if test="${journal.getDiscipline().getId() == discipline.id}"> selected </c:if>
                                >${discipline.discName}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td>
                        <form:label path="student.id">
                            <spring:message text="Student"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_student" size="1" path="student.id">
                            <c:forEach items="${getStudents}" var="student">
                                <option value="${student.id}"
                                        <c:if test="${journal.getStudent().getId() == student.id}"> selected </c:if>
                                >${student.firstName} ${student.lastName}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="grade">
                            <spring:message text="Grade"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_grade" size="1" path="grade">
                            <option value="1" <c:if test="${journal.grade == 1}"> selected </c:if>>1</option>
                            <option value="2" <c:if test="${journal.grade == 2}"> selected </c:if>>2</option>
                            <option value="3" <c:if test="${journal.grade == 3}"> selected </c:if>>3</option>
                            <option value="4" <c:if test="${journal.grade == 4}"> selected </c:if>>4</option>
                            <option value="5" <c:if test="${journal.grade == 5}"> selected </c:if>>5</option>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="teacher.id">
                            <spring:message text="Teacher"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_teacher" size="1" path="teacher.id">
                            <sec:authorize access="hasRole('ROLE_TEACHER')">
                                <option name="teacherName" value="${teacherName.id}">${teacherName.firstName} ${teacherName.lastName}</option>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <c:forEach items="${getTeachers}" var="teacher">
                                    <option value="${teacher.id}"
                                            <c:if test="${journal.getTeacher().getId() == teacher.id}"> selected </c:if>
                                    >${teacher.firstName} ${teacher.lastName}</option>
                                </c:forEach>
                            </sec:authorize>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty journal.discipline.id}">
                            <input type="submit"
                                   value="<spring:message text="Edit journal"/>"/>
                        </c:if>
                        <c:if test="${empty journal.discipline.id}">
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
