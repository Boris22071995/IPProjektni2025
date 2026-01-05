<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>FacultyApp | Login</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<div class="login-wrapper">
    <div class="login-card shadow">

        <h1>FacultyApp</h1>
        <h3>Welcome</h3>

        <form method="post" action="${pageContext.request.contextPath}/login">

            <div class="form-group mb-3">
                <label>Username</label>
                <input class="form-control" name="username" required>
            </div>

            <div class="form-group mb-3">
                <label>Password</label>
                <input type="password" class="form-control" name="password" required>
            </div>

            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <button class="btn btn-warning w-100">Login</button>

        </form>
    </div>
</div>

</body>
</html>
