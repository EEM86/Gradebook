<%@ page import="java.util.Date" %>
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
