<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 13.04.2019
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
    <style>
        body {
            background-color: lightblue;
        }
    </style>
</head>
<body>
    ${message}
    <p style="text-align: center">
    <%= new Date()%>
    </p>
</body>
</html>
