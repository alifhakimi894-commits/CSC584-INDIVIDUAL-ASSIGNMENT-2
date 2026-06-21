package com.assignment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection - JDBC utility class.
 *
 * Centralises the connection details for the NetBeans-bundled Java DB
 * (Apache Derby) database "StudentProfilesDB".
 *
 * HOW TO SET THIS UP IN NETBEANS (see README.md for full steps):
 * 1. Open the "Services" tab -> Databases -> Java DB.
 * 2. Right-click "Java DB" -> "Create Database..."
 *      Database Name : StudentProfilesDB
 *      User Name     : admin
 *      Password      : admin
 * 3. NetBeans will start the Derby network server automatically
 *    (default port 1527) and create the connection.
 * 4. Run the database.sql script against that connection
 *    (right-click the connection -> Execute Command...) to create
 *    the PROFILE table.
 *
 * If you used different DB name / user / password when creating the
 * database in NetBeans, just update the constants below to match.
 */
public class DBConnection {

    // ----- Connection settings for NetBeans Java DB (Derby) -----
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/StudentProfilesDB";
    private static final String USER = "student";
    private static final String PASSWORD = "student123";

    /**
     * Opens and returns a new JDBC Connection to StudentProfilesDB.
     * The caller is responsible for closing the connection
     * (try-with-resources is recommended).
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Derby Client JDBC Driver not found. "
                    + "Make sure derbyclient.jar is added to the project's "
                    + "WEB-INF/lib folder (NetBeans usually adds this "
                    + "automatically once Java DB is registered).", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * TEMPORARY DIAGNOSTIC METHOD.
     * Returns a human-readable string describing exactly which physical
     * database/schema/catalog the live JDBC connection is actually
     * pointing to. Useful for tracking down "wrong database" issues.
     * Safe to delete once the connection problem is resolved.
     */
    public static String diagnose() {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = getConnection()) {
            sb.append("Connected OK.\n");
            sb.append("URL constant used: ").append(URL).append("\n");
            sb.append("conn.getCatalog(): ").append(conn.getCatalog()).append("\n");
            sb.append("conn.getSchema(): ").append(conn.getSchema()).append("\n");
            sb.append("Metadata URL: ").append(conn.getMetaData().getURL()).append("\n");
            sb.append("Metadata UserName: ").append(conn.getMetaData().getUserName()).append("\n");

            try (java.sql.Statement st = conn.createStatement();
                    java.sql.ResultSet rs = st.executeQuery("SELECT COUNT(*) AS C FROM STUDENT.PROFILE")) {
                if (rs.next()) {
                    sb.append("Row count in STUDENT.PROFILE: ").append(rs.getInt("C")).append("\n");
                }
            } catch (SQLException e) {
                sb.append("Could NOT query STUDENT.PROFILE from this connection: ")
                        .append(e.getMessage()).append("\n");
            }

            try (java.sql.Statement st2 = conn.createStatement();
                    java.sql.ResultSet rs2 = st2.executeQuery("SELECT COUNT(*) AS C FROM PROFILE")) {
                if (rs2.next()) {
                    sb.append("Row count in PROFILE (unqualified): ").append(rs2.getInt("C")).append("\n");
                }
            } catch (SQLException e) {
                sb.append("Could NOT query unqualified PROFILE from this connection: ")
                        .append(e.getMessage()).append("\n");
            }

        } catch (SQLException e) {
            sb.append("Connection FAILED: ").append(e.getMessage()).append("\n");
        }
        return sb.toString();
    }
}
