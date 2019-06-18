<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <title>Container</title>
</head>
<body>
<div><jsp:include page="/"/></div>
<section>
    <c:if test="${!empty getContainers}">
        <table class="tg">
            <tr>
                <th width="40">ID</th>
                <th width="40">Parent Id</th>
                <th width="120">Name</th>
                <th width="80">Chief</th>
                <th width="80">Type</th>
                <th width="100">Institution City</th>
                <th width="120">Institution Address</th>
                <th width="80">Phone</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${getContainers}" var="container">
                <tr>
                    <td>${container.id}</td>
                    <td>${container.parentId}</td>
                    <td>${container.name}</td>
                    <td>${container.chief.firstName} ${container.chief.lastName}</td>
                    <td>${container.type.typeName}</td>
                    <td>${container.institutionCity}</td>
                    <td>${container.institutionAddress}</td>
                    <td>${container.phone}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td><a href="<c:url value='/containers/edit/${container.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/containers/delete/${container.id}'/>">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <h1>Add a Container</h1>

    <c:url var="addAction" value="/containers/add"/>

    <form:form action="${addAction}" modelAttribute="container">
        <table>
            <c:if test="${!empty container.name}">
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
                    <form:label path="parentId">
                        <spring:message text="parentId"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" min="1" path="parentId"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="chief.firstName">
                        <spring:message text="Chief name"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="text" path="chief.firstName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="chief.lastName">
                        <spring:message text="Chief surname"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="text" path="chief.lastName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="type.typeName">
                        <spring:message text="Type"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="text" path="type.typeName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="institutionCity">
                        <spring:message text="InstitutionCity"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="institutionCity"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="institutionAddress">
                        <spring:message text="InstitutionAddress"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="institutionAddress"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="Phone">
                        <spring:message text="phone"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="phone"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty container.name}">
                        <input type="submit"
                               value="<spring:message text="Edit Container"/>"/>
                    </c:if>
                    <c:if test="${empty container.name}">
                        <input type="submit"
                               value="<spring:message text="Add Container"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</section>

</body>
</html>
