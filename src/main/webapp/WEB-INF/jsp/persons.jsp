<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'}).val();
        });
    </script>
    <title>Persons</title>
</head>
<body>
<div>
    <jsp:include page="/"/>
</div>

<section>
    <br>
    <br>
    <form action="persons" method="post" >
        <input type="text" name="lastName" placeholder="Enter last name to search">
        <button>Find</button>
    </form>
    <br>
    <br>
    <c:if test="${!empty getPersons}">
        <table class="tg" width="100%">
            <tr>
                <th width="60">ID</th>
                <th width="60">role</th>
                <th width="120">FirstName</th>
                <th width="120">LastName</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th width="120">email</th>
                    <th width="120">phone</th>
                    <th width="120">address</th>
                    <th width="120">birthday</th>
                </sec:authorize>
                <th width="60">department</th>
                <th width="120">curator</th>
                <th width="60">group</th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th width="120">login</th>
                    <th width="120">password</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${getPersons}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.role.id}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>${person.email}</td>
                        <td>${person.phone}</td>
                        <td>${person.address}</td>
                        <td>${person.birthday}</td>
                    </sec:authorize>
                    <td>${person.department.name}</td>
                    <td>${person.curator.firstName} ${person.curator.lastName}</td>
                    <td>${person.group.name}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>${person.login}</td>
                        <td>${person.password}</td>
                        <td><a href="<c:url value='/edit/${person.id}'/>">Edit</a></td>
                        <td><a href="<c:url value='/delete/${person.id}'/>">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h1>Add a Person</h1>

        <c:url var="addAction" value="/persons/add"/>

        <form:form action="${addAction}" modelAttribute="person">
            <table>
                <c:if test="${!empty person.lastName}">
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
                        <form:label path="role.id">
                            <spring:message text="Role"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="role.id"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="firstName">
                            <spring:message text="FirstName"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="firstName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="lastName">
                            <spring:message text="LastName"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="lastName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="email">
                            <spring:message text="Email"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="email" path="email"/>
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
                    <td>
                        <form:label path="address">
                            <spring:message text="Address"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="address"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="birthday">
                            <spring:message text="Birthday"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" id="datepicker" path="birthday"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="department.name">
                            <spring:message text="Department"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="department.name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="curator.firstName">
                            <spring:message text="Curator name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="curator.firstName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="curator.lastName">
                            <spring:message text="Curator surname"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="curator.lastName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="group.name">
                            <spring:message text="Group"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input type="text" path="group.name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="login">
                            <spring:message text="Login"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="login"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">
                            <spring:message text="Password"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="password"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty person.lastName}">
                            <input type="submit"
                                   value="<spring:message text="Edit Person"/>"/>
                        </c:if>
                        <c:if test="${empty person.lastName}">
                            <input type="submit"
                                   value="<spring:message text="Add Person"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </sec:authorize>
</section>

</body>
</html>
