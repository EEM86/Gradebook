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
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <section>
        <c:if test="${!empty getContainers}">
            <table class="tg">
                <tr>
                    <th width="40">ID</th>
                    <th width="200">Parent</th>
                    <th width="200">Name</th>
                    <th width="150">Chief</th>
                    <th width="80">Type</th>
                    <th width="100">Institution City</th>
                    <th width="200">Institution Address</th>
                    <th width="80">Phone</th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <th width="60">Edit</th>
                        <th width="60">Delete</th>
                    </sec:authorize>
                </tr>
                <c:forEach items="${getContainers}" var="container">
                    <tr>
                        <td>${container.id}</td>
                        <td>${container.parent.name}</td>
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
                        <form:label path="parent.id">
                            <spring:message text="Parent name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_parent" size="1" path="parent.id">
                            <c:forEach items="${getContainers}" var="getContainer">
                                <option value="">No container</option>
                                <option value="${getContainer.id}"
                                        <c:if test="${container.getParent().getId() == getContainer.id}"> selected </c:if>
                                >${getContainer.name}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="Container name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="chief.id">
                            <spring:message text="Chief"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_chief" size="1" path="chief.id">
                            <c:forEach items="${getChiefs}" var="chief1">
                                <option value="${chief1.id}"
                                        <c:if test="${container.getChief().getId() == chief1.id}"> selected </c:if>
                                >${chief1.firstName} ${chief1.lastName}</option>  ${chief.id}
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="type.id">
                            <spring:message text="Type"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_type" size="1" path="type.id">
                            <c:forEach items="${getBranchTypes}" var="type">
                                <option value="${type.id}"
                                        <c:if test="${container.getType().getId() == type.id}"> selected </c:if>
                                >${type.typeName}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="institutionCity">
                            <spring:message text="Institution City"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="institutionCity"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="institutionAddress">
                            <spring:message text="Institution Address"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="institutionAddress"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="phone">
                            <spring:message text="Phone"/>
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
</sec:authorize>

</body>
</html>
