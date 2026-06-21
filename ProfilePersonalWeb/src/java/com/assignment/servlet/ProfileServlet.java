package com.assignment.servlet;

import com.assignment.bean.ProfileBean;
import com.assignment.dao.ProfileDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ProfileServlet - Controller component of MVC.
 *
 * Single front-controller style servlet that handles every operation
 * in the Profile Management System via an "action" parameter:
 *
 *   action=insert  -> save a new profile, then forward to profile.jsp
 *   action=list    -> retrieve all profiles, forward to viewProfiles.jsp
 *   action=search  -> search by Student ID / Name   (Option A)
 *   action=filter  -> filter by Programme / Hobbies (Option D)
 *   action=edit    -> load one profile for editing, forward to editProfile.jsp
 *   action=update  -> save edited profile           (Option B)
 *   action=delete  -> remove a profile               (Option C)
 *
 * No action (plain GET to /ProfileServlet) defaults to listing all profiles.
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    private final ProfileDAO profileDAO = new ProfileDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "insert":
                    handleInsert(request, response);
                    break;
                case "search":
                    handleSearch(request, response);
                    break;
                case "filter":
                    handleFilter(request, response);
                    break;
                case "edit":
                    handleEditForm(request, response);
                    break;
                case "view":
                    handleView(request, response);
                    break;
                case "update":
                    handleUpdate(request, response);
                    break;
                case "delete":
                    handleDelete(request, response);
                    break;
                case "list":
                default:
                    handleList(request, response);
                    break;
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage",
                    "Database error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Builds a ProfileBean (Model) from request parameters.
     */
    private ProfileBean buildBeanFromRequest(HttpServletRequest request) {
        ProfileBean profile = new ProfileBean();
        profile.setStudentID(request.getParameter("studentID"));
        profile.setName(request.getParameter("name"));
        profile.setProgramme(request.getParameter("programme"));
        profile.setEmail(request.getParameter("email"));
        profile.setHobbies(request.getParameter("hobbies"));
        profile.setIntroduction(request.getParameter("introduction"));
        return profile;
    }

    /**
     * action=insert : Receives the registration form (POST), creates a
     * ProfileBean, and stores it into the database via JDBC.
     */
    private void handleInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ProfileBean profile = buildBeanFromRequest(request);

        if (profile.getStudentID() == null || profile.getStudentID().trim().isEmpty()) {
            request.setAttribute("errorMessage", "Student ID is required.");
            request.getRequestDispatcher("index.html").forward(request, response);
            return;
        }

        if (profileDAO.profileExists(profile.getStudentID())) {
            request.setAttribute("errorMessage",
                    "A profile with Student ID '" + profile.getStudentID()
                    + "' already exists. Please use a different ID or edit the existing profile.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        profileDAO.insertProfile(profile);

        // Re-fetch from DB to confirm what was actually persisted
        ProfileBean saved = profileDAO.getProfileById(profile.getStudentID());
        request.setAttribute("profile", saved);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    /**
     * action=list : Retrieves every profile and forwards to viewProfiles.jsp.
     */
    private void handleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        List<ProfileBean> profiles = profileDAO.getAllProfiles();
        request.setAttribute("profileList", profiles);
        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }

    /**
     * action=search (Option A - Search Profile): search by Student ID or Name.
     */
    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String keyword = request.getParameter("keyword");
        List<ProfileBean> profiles;

        if (keyword == null || keyword.trim().isEmpty()) {
            profiles = profileDAO.getAllProfiles();
        } else {
            profiles = profileDAO.searchProfiles(keyword.trim());
        }

        request.setAttribute("profileList", profiles);
        request.setAttribute("searchKeyword", keyword);
        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }

    /**
     * action=filter (Option D - Filter Profiles): filter by Programme / Hobbies.
     */
    private void handleFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String programme = request.getParameter("programme");
        String hobbies = request.getParameter("hobbies");

        List<ProfileBean> profiles = profileDAO.filterProfiles(programme, hobbies);

        request.setAttribute("profileList", profiles);
        request.setAttribute("filterProgramme", programme);
        request.setAttribute("filterHobbies", hobbies);
        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }

    /**
     * action=view : loads a single profile (read-only) and forwards to
     * profile.jsp — used by the "View" link on each card in viewProfiles.jsp.
     */
    private void handleView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String studentID = request.getParameter("studentID");
        ProfileBean profile = profileDAO.getProfileById(studentID);

        if (profile == null) {
            request.setAttribute("errorMessage",
                    "No profile found with Student ID '" + studentID + "'.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("profile", profile);
        request.setAttribute("viewOnly", true);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    /**
     * action=edit (Option B - Edit Profile, step 1): loads the existing
     * profile into editProfile.jsp so the user can modify it.
     */
    private void handleEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String studentID = request.getParameter("studentID");
        ProfileBean profile = profileDAO.getProfileById(studentID);

        if (profile == null) {
            request.setAttribute("errorMessage",
                    "No profile found with Student ID '" + studentID + "'.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("profile", profile);
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    /**
     * action=update (Option B - Edit Profile, step 2): persists the
     * edited fields back into the database.
     */
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ProfileBean profile = buildBeanFromRequest(request);
        profileDAO.updateProfile(profile);

        ProfileBean updated = profileDAO.getProfileById(profile.getStudentID());
        request.setAttribute("profile", updated);
        request.setAttribute("updated", true);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    /**
     * action=delete (Option C - Delete Profile): removes the profile record,
     * then redirects back to the profile list.
     */
    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {

        String studentID = request.getParameter("studentID");
        profileDAO.deleteProfile(studentID);

        // Redirect (not forward) so a page refresh doesn't repeat the delete
        response.sendRedirect("ProfileServlet?action=list&deleted=true");
    }
}
