<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Branch type</title>

</head>
<body>
<div><jsp:include page="/"/></div>
<section>
    <c:if test="${!empty getbranchtypes}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="80">Name</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${getbranchtypes}" var="branchtype">
                <tr>
                    <td>${branchtype.id}</td>
                    <td>${branchtype.typeName}</td>
                    <td><a href="<c:url value='/branchtypes/edit/${branchtype.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/branchtypes/delete/${branchtype.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <h1>Add a branch type</h1>

    <c:url var="addAction" value="/branchtypes/add"/>

    <form:form action="${addAction}" modelAttribute="branchtype">
        <table>
            <c:if test="${!empty branchtype.id}">
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
                    <form:label path="typeName">
                        <spring:message text="Type Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="typeName" required="true" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty branchtype.id}">
                        <input type="submit"
                               value="<spring:message text="Edit branch type"/>"/>
                    </c:if>
                    <c:if test="${empty branchtype.id}">
                        <input type="submit"
                               value="<spring:message text="Add branch type"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</section>
</body>
</html>
