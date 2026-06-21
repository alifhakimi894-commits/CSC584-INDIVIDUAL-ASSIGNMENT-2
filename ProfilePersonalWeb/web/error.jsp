<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Something Went Wrong</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body class="center-body">

<div class="error-card">
    <div class="icon">⚠️</div>
    <h2>Something Went Wrong</h2>
    <p>
        <c:choose>
            <c:when test="${not empty errorMessage}">
                <c:out value="${errorMessage}"/>
            </c:when>
            <c:otherwise>
                An unexpected error occurred while processing your request.
            </c:otherwise>
        </c:choose>
    </p>

    <div class="action-buttons" style="justify-content:center;">
        <a href="ProfileServlet?action=list" class="btn btn-secondary">📋 View All Profiles</a>
        <a href="index.html" class="btn btn-primary">+ Register New Profile</a>
    </div>
</div>

</body>
</html>
