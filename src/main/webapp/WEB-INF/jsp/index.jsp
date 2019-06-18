<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>

<body>
<header>
    <div class="href">
        <div class="href1">
            <a href="/Gradebook/profile">Profile</a>
        </div>
        <div class="href1">
            <a href="/Gradebook/persons">Person</a>
        </div>
        <div class="href1">
            <a href="/Gradebook/disciplines">Disciplines</a>
        </div>
        <div class="href1">
            <a href="/Gradebook/messages">Messages</a>
        </div>
        <div class="href1">
            <a href="/Gradebook/containers">Containers</a>
        </div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="href1">
                <a href="/Gradebook/branchtypes">Branch Type</a>
            </div>
            <div class="href1">
                <a href="/Gradebook/roles">Roles</a>
            </div>
        </sec:authorize>
        <div class="href1">
            <a href="/Gradebook/journal">Journal</a>
        </div>
        <sec:authorize access="hasAnyAuthority('ROLE_TEACHER', 'ROLE_ADMIN')">
            <div class="href1">
                <a href="/Gradebook/lessonsplan">Lessons Plan</a>
            </div>
        </sec:authorize>
    </div>

    <sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_TEACHER')">
        <div class="href">
            <hr>
            <div class="href1"> REST API: </div>
            <div class="href1">
                <a href="person/all">Persons - rest</a>
            </div>

            <div class="href1">
                <a href="discipline/all">Disciplines - rest</a>
            </div>

            <div class="href1">
                <a href="message-rest/all">Messages - rest</a>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="href1">
                    <a href="container/all">Containers - rest</a>
                </div>
                <div class="href1">
                    <a href="branchtypes-rest/all">Branch Type - rest</a>
                </div>
                <div class="href1">
                    <a href="role/all">Roles - rest</a>
                </div>
            </sec:authorize>
            <div class="href1">
                <a href="journal-rest/all">Journal - rest</a>
            </div>
            <div class="href1">
                <a href="lessonsplan-rest/all">Lessons Plan - rest</a>
            </div>
        </div>
    </sec:authorize>
</header>

<div class="logout" margin-left="20px">
    Вы вошли как:
    <%--<b><sec:authentication property="principal.username"/></b>--%>

    <br>с ролью:
    <%--<b><sec:authentication property="principal.authorities"/></b>--%>
    <br><a href="${pageContext.request.contextPath}/logout"..>Sign Out</a>
</div>
</body>

</html>
