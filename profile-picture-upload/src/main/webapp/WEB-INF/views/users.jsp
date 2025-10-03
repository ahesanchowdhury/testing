<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>All Users</h2>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Profile Picture</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userName}</td>
            <td>
                <img src="/${user.profilePic}" width="100" height="100"/>
            </td>
        </tr>
    </c:forEach>
</table>

<br>

</body>
</html>
