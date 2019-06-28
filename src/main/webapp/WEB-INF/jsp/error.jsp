<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Upps, seems you did something wrong...</h1>
Please, contact the support: <a href="mailto:GradebookMessenger@gmail.com"/>
<div><c:out value="${exception}"/></div>
</body>
</html>
