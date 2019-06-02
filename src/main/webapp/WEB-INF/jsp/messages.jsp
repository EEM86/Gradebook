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
    <c:if test="${!empty getMessage}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="80">Receiver</th>
                <th width="80">Sender</th>
                <th width="100">message</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${getMessage}" var="message">
                <tr>
                    <td>${message.id}</td>
                    <td>${message.receiver_id}</td>
                    <td>${message.sender_id}</td>
                    <td>${message.message}</td>
                    <td><a href="<c:url value='/message/edit/${message.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/message/delete/${message.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>
    <h1>Add message</h1>
    <c:url var="addAction" value="/message/add"/>
    <form:form action="${addAction}" modelAttribute="message">
        <table>
            <c:if test="${!empty message.disc_id}">
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
                    <form:label value="receiver_id" path="receiver_id">
                        <spring:message text="Receiver"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="0" path="receiver_id"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="sender_id">
                        <spring:message text="Sender"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="0" path="sender_id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="message">
                        <spring:message text="Message"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="0" path="message"/>
                </td>
            </tr>
             <tr>
                <td colspan="2">
                    <c:if test="${!empty message.receiver_id}">
                        <input type="submit"
                               value="<spring:message text="Edit message"/>"/>
                    </c:if>
                    <c:if test="${empty message.receiver_id}">
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
