<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${profile.name}"/>'s Profile</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="center-body">

<c:if test="${empty viewOnly}">
    <c:if test="${not empty updated}">
        <div class="alert alert-success" style="max-width:1000px;">
            ✅ Profile updated successfully.
        </div>
    </c:if>
    <c:if test="${empty updated}">
        <div class="alert alert-success" style="max-width:1000px;">
            ✅ Profile saved successfully to the database.
        </div>
    </c:if>
</c:if>

<div class="profile-card">

    <!-- LEFT SIDEBAR -->
    <div class="profile-sidebar">

        <div class="profile-avatar">🎓</div>

        <h1><c:out value="${profile.name}"/></h1>

        <p class="student-id"><c:out value="${profile.programme}"/></p>

        <div class="student-badge">UiTM Student</div>

        <div class="mini-info">
            <div class="mini-label">Student ID</div>
            <div class="mini-value"><c:out value="${profile.studentID}"/></div>
        </div>

        <div class="mini-info">
            <div class="mini-label">Email Address</div>
            <div class="mini-value"><c:out value="${profile.email}"/></div>
        </div>

        <div class="mini-info">
            <div class="mini-label">Hobbies &amp; Interests</div>
            <div class="mini-value"><c:out value="${profile.hobbies}"/></div>
        </div>

    </div>

    <!-- RIGHT CONTENT -->
    <div class="profile-main">

        <div class="bio-box">
            <div class="section-title">Student Overview</div>
            <p>
                Welcome to the UiTM student profile portal.
                This profile contains the personal information,
                academic programme, interests, and background
                details of the registered student, as stored in
                the StudentProfilesDB database.
            </p>
        </div>

        <div class="info-grid">
            <div class="info-card">
                <h3>Programme</h3>
                <p><c:out value="${profile.programme}"/></p>
            </div>

            <div class="info-card">
                <h3>Student Email</h3>
                <p><c:out value="${profile.email}"/></p>
            </div>

            <div class="info-card">
                <h3>Matric Number</h3>
                <p><c:out value="${profile.studentID}"/></p>
            </div>

            <div class="info-card">
                <h3>Interests</h3>
                <p><c:out value="${profile.hobbies}"/></p>
            </div>
        </div>

        <div class="section-title">Personal Background</div>

        <div class="bio-box">
            <p>"<c:out value="${profile.introduction}"/>"</p>
        </div>

        <div class="action-buttons">
            <a href="ProfileServlet?action=edit&studentID=${profile.studentID}" class="btn btn-secondary">✏️ Edit Profile</a>
            <a href="ProfileServlet?action=list" class="btn btn-secondary">📋 View All Profiles</a>
            <a href="index.html" class="btn btn-primary">+ Register Another Profile</a>
        </div>

    </div>

</div>

</body>
</html>
