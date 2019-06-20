<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Message</title>

</head>
<body>
<div><jsp:include page="/"/></div>
<section>
    <c:if test="${!empty getMessages}">
        <table class="tg">
            <tr>
                <th width="40">ID</th>
                <th width="80">Receiver</th>
                <th width="80">Sender</th>
                <th width="200">message</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th width="60">Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${getMessages}" var="message">
                <tr>
                    <td>${message.id}</td>
                    <td>${message.receiver.firstName} ${message.receiver.lastName}</td>
                    <td>${message.sender.firstName} ${message.sender.lastName}</td>
                    <td>${message.messageText}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="<c:url value='/messages/delete/${message.id}'/>">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>
    <h1>Add message</h1>
    <c:url var="addAction" value="/messages/add"/>
    <form:form action="${addAction}" modelAttribute="message">
        <table>
            <tr>
                <td>
                    <form:label path="receiver.id">
                        <spring:message text="Receiver"/>
                    </form:label>
                </td>
                <td>
                    <form:select name="select_receiver" size="1" path="receiver.id">
                        <c:forEach items="${getPersons}" var="person">
                            <option value="${person.id}"
                                    <c:if test="${message.getReceiver().getId() == person.id}"> selected </c:if>
                            >${person.firstName} ${person.lastName}</option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="sender.id">
                        <spring:message text="Sender"/>
                    </form:label>
                </td>
                <td>
                    <form:input value="${idSender.firstName} ${idSender.lastName}" path="sender.id" readonly="true" disabled="true"/>
                    <form:hidden value="${idSender.getId()}" path="sender.id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="messageText">
                        <spring:message text="Message Text"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="text" size="100px" path="messageText"/>
                </td>
            </tr>

             <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Add message"/>"/>
                </td>
            </tr>

        </table>
    </form:form>
</section>

</body>
</html>
