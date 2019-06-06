<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>

<body>
<header>
    <div class="href">
        <div class="href1">
            <a href="">Profile</a>
        </div>
        <div class="href1">
            <a href="persons">Person</a>
        </div>
        <div class="href1">
            <a href="disciplines">Disciplines</a>
        </div>
        <div class="href1">
            <a href="messages">Messages</a>
        </div>

        <div class="href1">
            <a href="containers">Containers</a>
        </div>
        <div class="href1">
            <a href="branchtypes">Branch Type</a>
        </div>
        <div class="href1">
            <a href="roles">Roles</a>
        </div>
        <div class="href1">
            <a href="journal">Journal</a>
        </div>
        <div class="href1">
            <a href="lessonsplan">Lessons Plan</a>
        </div>
    </div>

    <div class="href">
        <hr>
        <div class="href1"> REST API: </div>
        <div class="href1">
            <a href="persons-rest">Persons - rest</a>
        </div>

        <div class="href1">
            <a href="disciplines-rest">Disciplines - rest</a>
        </div>

        <div class="href1">
            <a href="message-rest/all">Messages - rest</a>
        </div>

        <div class="href1">
            <a href="containers-rest">Containers - rest</a>
        </div>
        <div class="href1">
            <a href="branchtypes-rest/all">Branch Type - rest</a>
        </div>
        <div class="href1">
            <a href="roles-rest">Roles - rest</a>
        </div>
        <div class="href1">
            <a href="journal-rest/all">Journal - rest</a>
        </div>
        <div class="href1">
            <a href="lessonsplan-rest/all">Lessons Plan - rest</a>
        </div>
    </div>
</header>

<div class="logout">
    Вы вошли как:
    <security:authentication property= "principal.username"/> с ролью:
    <b><security:authentication property= "principal.authorities"/></b>
    <a href="${pageContext.request.contextPath}/logout"..>Sign Out</a>
</div>
</body>

</html>

