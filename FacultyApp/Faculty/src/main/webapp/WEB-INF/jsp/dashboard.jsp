<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>StudentApp</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

<jsp:include page="header.jsp"/>
<jsp:include page="sidebar.jsp"/>

<div class="content" id="content">
  <jsp:include page="/WEB-INF/jsp/${page}.jsp"/>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

</body>
</html>
