package com.assignment.dao;

import com.assignment.bean.ProfileBean;
import com.assignment.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProfileDAO - Data Access Object.
 *
 * Encapsulates every JDBC operation used by the Profile Management
 * System: insert, retrieve all, retrieve one, search (by Student ID
 * or Name), filter (by Programme or Hobbies), update, and delete.
 *
 * Keeping all SQL in one class keeps the Servlet (Controller) thin
 * and free of JDBC boilerplate.
 */
public class ProfileDAO {

    /**
     * Inserts a new profile record into the PROFILE table.
     */
    public boolean insertProfile(ProfileBean profile) throws SQLException {
        String sql = "INSERT INTO PROFILE (studentID, name, programme, email, hobbies, introduction) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, profile.getStudentID());
            ps.setString(2, profile.getName());
            ps.setString(3, profile.getProgramme());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntroduction());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Retrieves every profile record, ordered by studentID.
     */
    public List<ProfileBean> getAllProfiles() throws SQLException {
        List<ProfileBean> list = new ArrayList<>();
        String sql = "SELECT studentID, name, programme, email, hobbies, introduction "
                + "FROM PROFILE ORDER BY studentID";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    /**
     * Retrieves a single profile by its exact Student ID.
     * Used by profile.jsp after a save, and by editProfile.jsp before editing.
     */
    public ProfileBean getProfileById(String studentID) throws SQLException {
        String sql = "SELECT studentID, name, programme, email, hobbies, introduction "
                + "FROM PROFILE WHERE studentID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    /**
     * Feature (Option A - Search Profile):
     * Searches profiles by Student ID OR Name (partial, case-insensitive match).
     */
    public List<ProfileBean> searchProfiles(String keyword) throws SQLException {
        List<ProfileBean> list = new ArrayList<>();
        String sql = "SELECT studentID, name, programme, email, hobbies, introduction "
                + "FROM PROFILE "
                + "WHERE UPPER(studentID) LIKE ? OR UPPER(name) LIKE ? "
                + "ORDER BY studentID";

        String likeKeyword = "%" + keyword.toUpperCase() + "%";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    /**
     * Feature (Option D - Filter Profiles):
     * Filters profiles by Programme and/or Hobbies. Either parameter may be
     * null/empty, in which case that condition is skipped.
     */
    public List<ProfileBean> filterProfiles(String programme, String hobbies) throws SQLException {
        List<ProfileBean> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT studentID, name, programme, email, hobbies, introduction FROM PROFILE WHERE 1=1");

        boolean hasProgramme = programme != null && !programme.trim().isEmpty();
        boolean hasHobbies = hobbies != null && !hobbies.trim().isEmpty();

        if (hasProgramme) {
            sql.append(" AND UPPER(programme) LIKE ?");
        }
        if (hasHobbies) {
            sql.append(" AND UPPER(hobbies) LIKE ?");
        }
        sql.append(" ORDER BY studentID");

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (hasProgramme) {
                ps.setString(index++, "%" + programme.toUpperCase() + "%");
            }
            if (hasHobbies) {
                ps.setString(index++, "%" + hobbies.toUpperCase() + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    /**
     * Feature (Option B - Edit Profile):
     * Updates every editable field of an existing profile, identified by studentID.
     */
    public boolean updateProfile(ProfileBean profile) throws SQLException {
        String sql = "UPDATE PROFILE SET name = ?, programme = ?, email = ?, "
                + "hobbies = ?, introduction = ? WHERE studentID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, profile.getName());
            ps.setString(2, profile.getProgramme());
            ps.setString(3, profile.getEmail());
            ps.setString(4, profile.getHobbies());
            ps.setString(5, profile.getIntroduction());
            ps.setString(6, profile.getStudentID());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Feature (Option C - Delete Profile):
     * Removes a profile record identified by studentID.
     */
    public boolean deleteProfile(String studentID) throws SQLException {
        String sql = "DELETE FROM PROFILE WHERE studentID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentID);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Checks whether a Student ID already exists (used to prevent
     * duplicate primary keys before insert).
     */
    public boolean profileExists(String studentID) throws SQLException {
        String sql = "SELECT 1 FROM PROFILE WHERE studentID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /**
     * Maps the current row of a ResultSet to a ProfileBean.
     */
    private ProfileBean mapRow(ResultSet rs) throws SQLException {
        ProfileBean p = new ProfileBean();
        p.setStudentID(rs.getString("studentID"));
        p.setName(rs.getString("name"));
        p.setProgramme(rs.getString("programme"));
        p.setEmail(rs.getString("email"));
        p.setHobbies(rs.getString("hobbies"));
        p.setIntroduction(rs.getString("introduction"));
        return p;
    }
}
