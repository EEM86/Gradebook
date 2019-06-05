<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html">
    <meta charset="utf=8">
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
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${getMessages}" var="message">
                <tr>
                    <td>${message.id}</td>
                    <td>${message.receiverId}</td>
                    <td>${message.senderId}</td>
                    <td>${message.messageText}</td>
                    <td><a href="<c:url value='/messages/edit/${message.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/messages/delete/${message.id}'/>">Delete</a></td>
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
            <c:if test="${!empty message.receiverId}">
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
                    <form:label value="receiverId" path="receiverId">
                        <spring:message text="Receiver"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" pattern="[0-9]" path="receiverId"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="senderId">
                        <spring:message text="Sender"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" pattern="[0-9]" path="senderId"/>
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
                    <c:if test="${!empty message.receiverId}">
                        <input type="submit"
                               value="<spring:message text="Edit message"/>"/>
                    </c:if>
                    <c:if test="${empty message.receiverId}">
                        <input type="submit"
                               value="<spring:message text="Add message"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</section>

</body>
</html>
