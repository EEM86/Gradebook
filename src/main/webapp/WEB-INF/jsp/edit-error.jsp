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
    <title>Error in edit</title>
    <h1>Error was occured during person edit</h1>
</head>
<body>
    <a href="/Gradebook/">Back to main menu</a>
    <br/>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h1>Add a Person</h1>

        <c:url var="addAction" value="/persons/add"/>

        <form:form action="${addAction}" modelAttribute="person">
            <table>
                <c:if test="${!empty person.id}">
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
                        <form:select name="select_role" size="1" path="role.id">
                            <c:forEach items="${getRoles}" var="role">
                                <option value="${role.id}"
                                        <c:if test="${person.getRole().getId() == role.id}"> selected </c:if>
                                >${role.roleName}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="firstName">
                            <spring:message text="First Name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="firstName"/>
                        <form:errors path="firstName" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="lastName">
                            <spring:message text="Last Name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="lastName"/>
                        <form:errors path="lastName" cssClass="error"/>
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
                        <form:errors path="email" cssClass="error"/>
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
                        <form:errors path="phone" cssClass="error"/>
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
                        <form:errors path="address" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="birthday">
                            <spring:message text="Birthday"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input id="datepicker" path="birthday" value = "1980-12-12" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/>
                        <form:errors path="birthday" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="department.id">
                            <spring:message text="Department"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_group" size="1" path="department.id">
                            <option value="">No department</option>
                            <c:forEach items="${getDepartments}" var="department">
                                <option value="${department.id}"
                                        <c:if test="${person.getDepartment().getId() == department.id}"> selected </c:if>
                                >${department.name}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="curator.id">
                            <spring:message text="Curator"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_curator" size="1" path="curator.id">
                            <option value="">No curator</option>
                            <c:forEach items="${getCurators}" var="curator">
                                <option value="${curator.id}"
                                        <c:if test="${person.getCurator().getId() == curator.id}"> selected </c:if>
                                >${curator.firstName} ${curator.lastName}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="group.id">
                            <spring:message text="Group"/>
                        </form:label>
                    </td>
                    <td>
                        <form:select name="select_group" size="1" path="group.id">
                            <option value="">No group</option>
                            <c:forEach items="${getGroups}" var="group">
                                <option value="${group.id}"
                                        <c:if test="${person.getGroup().getId() == group.id}"> selected </c:if>
                                >${group.name}</option>
                            </c:forEach>
                        </form:select>
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
                        <form:errors path="login" cssClass="error"/>
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
                        <form:errors path="password" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty person.id}">
                            <input type="submit"
                                   value="<spring:message text="Edit Person"/>"/>
                        </c:if>
                        <c:if test="${empty person.id}">
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
