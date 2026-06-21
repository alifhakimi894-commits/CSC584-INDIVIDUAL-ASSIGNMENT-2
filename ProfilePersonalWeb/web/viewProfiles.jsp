<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Student Profiles</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="page-wrap">

    <c:if test="${param.deleted == 'true'}">
        <div class="alert alert-success">🗑️ Profile deleted successfully.</div>
    </c:if>

    <div class="page-header">
        <div>
            <h2>📋 Student Profile Directory</h2>
            <p>${fn:length(profileList)} 
                <c:choose>
                    <c:when test="${not empty searchKeyword}">result(s) for "<c:out value="${searchKeyword}"/>"</c:when>
                    <c:otherwise>profile(s) found</c:otherwise>
                </c:choose>
            </p>
        </div>
        <a href="index.html" class="btn btn-primary">+ Register New Profile</a>
    </div>

    <!-- SEARCH (Option A) + FILTER (Option D) TOOLBAR -->
    <div class="toolbar">

        <form action="ProfileServlet" method="GET">
            <input type="hidden" name="action" value="search">
            <div class="field">
                <label>Search by Student ID or Name</label>
                <input type="text" name="keyword" placeholder="e.g. 2024123456 or Ahmad"
                       value="${searchKeyword}">
            </div>
            <button type="submit" class="btn btn-secondary">🔍 Search</button>
        </form>

        <form action="ProfileServlet" method="GET">
            <input type="hidden" name="action" value="filter">
            <div class="field">
                <label>Filter by Programme</label>
                <input type="text" name="programme" placeholder="e.g. Computer Science"
                       value="${filterProgramme}">
            </div>
            <div class="field">
                <label>Filter by Hobbies</label>
                <input type="text" name="hobbies" placeholder="e.g. Reading"
                       value="${filterHobbies}">
            </div>
            <button type="submit" class="btn btn-secondary">🧰 Filter</button>
        </form>

        <a href="ProfileServlet?action=list" class="btn btn-secondary btn-small" style="align-self:center;">↺ Reset</a>
    </div>

    <!-- PROFILE CARD GRID -->
    <div class="card-grid">

        <c:choose>
            <c:when test="${empty profileList}">
                <div class="empty-state">
                    <div class="icon">🗂️</div>
                    <p>No profiles found. Try a different search/filter, or register a new profile.</p>
                </div>
            </c:when>

            <c:otherwise>
                <c:forEach var="p" items="${profileList}">
                    <div class="profile-mini-card">
                        <div class="avatar-sm">🎓</div>
                        <h3><c:out value="${p.name}"/></h3>
                        <div class="mini-id">ID: <c:out value="${p.studentID}"/></div>

                        <div class="row">
                            <div class="label">Programme</div>
                            <div class="value"><c:out value="${p.programme}"/></div>
                        </div>
                        <div class="row">
                            <div class="label">Email</div>
                            <div class="value"><c:out value="${p.email}"/></div>
                        </div>
                        <div class="row">
                            <div class="label">Hobbies</div>
                            <div class="value"><c:out value="${p.hobbies}"/></div>
                        </div>

                        <div class="card-actions">
                            <a href="ProfileServlet?action=edit&studentID=${p.studentID}"
                               class="btn btn-secondary btn-small">✏️ Edit</a>

                            <a href="ProfileServlet?action=view&studentID=${p.studentID}"
                               class="btn btn-secondary btn-small">👁️ View</a>

                            <a href="ProfileServlet?action=delete&studentID=${p.studentID}"
                               class="btn btn-danger btn-small"
                               onclick="return confirm('Delete profile for ${p.name} (${p.studentID})? This cannot be undone.');">🗑️ Delete</a>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </div>

</div>

</body>
</html>
