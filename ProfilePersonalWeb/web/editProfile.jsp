<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile - <c:out value="${profile.name}"/></title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

<div class="form-container">

    <div class="form-header">
        <div class="person-icon">✏️</div>
        <h2>Edit Profile</h2>
        <p>Update the information for this student profile</p>
    </div>

    <div class="top-nav">
        <a href="ProfileServlet?action=list" class="nav-link">📋 Back to All Profiles</a>
    </div>

    <form action="ProfileServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="studentID" value="${profile.studentID}">

        <div class="form-group">
            <label>Student ID</label>
            <input type="text" value="${profile.studentID}" disabled
                   style="opacity:0.6; cursor:not-allowed;">
            <small style="color:#e9d8fd; font-size:12px;">Student ID cannot be changed.</small>
        </div>

        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="name" value="${profile.name}" required>
        </div>

        <div class="form-group">
            <label>Programme</label>
            <input type="text" name="programme" value="${profile.programme}" required>
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" value="${profile.email}" required>
        </div>

        <div class="form-group">
            <label>Hobbies</label>
            <input type="text" name="hobbies" value="${profile.hobbies}" required>
        </div>

        <div class="form-group">
            <label>Introduction</label>
            <textarea name="introduction" required><c:out value="${profile.introduction}"/></textarea>
        </div>

        <button type="submit" class="btn-submit">
            💾 Save Changes
        </button>

    </form>

</div>

</body>
</html>
