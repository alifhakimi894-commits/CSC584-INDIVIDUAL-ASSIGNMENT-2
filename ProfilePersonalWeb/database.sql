-- =====================================================================
-- database.sql
-- Profile Management System - CSC584 Individual Assignment 2
-- Database  : StudentProfilesDB
-- Engine    : Apache Derby (NetBeans "Java DB")
-- =====================================================================
--
-- HOW TO RUN THIS SCRIPT IN NETBEANS:
-- 1. Open the "Services" tab (Window > Services).
-- 2. Expand Databases > Java DB.
-- 3. Right-click "Java DB" -> "Create Database..."
--       Database Name : StudentProfilesDB
--       User Name     : admin
--       Password      : admin
--    (NetBeans creates the DB and a connection automatically.)
-- 4. Right-click the new connection
--    (jdbc:derby://localhost:1527/StudentProfilesDB) -> "Connect".
-- 5. Right-click the connection again -> "Execute Command..."
-- 6. Paste the contents of this file and run it (Run SQL / F6).
--
-- NOTE ON SYNTAX: Derby does not support "DROP TABLE IF EXISTS" or
-- "CREATE TABLE IF NOT EXISTS". The DROP TABLE statement below is
-- wrapped so it can simply be skipped/ignored the first time you run
-- this script (when the table does not exist yet, Derby will show an
-- error for that one line only - this is expected and safe to ignore).
-- =====================================================================

-- Drop the table first if you are re-running this script (ignore error
-- on first run, since the table will not exist yet).
DROP TABLE PROFILE;

-- Create the PROFILE table
CREATE TABLE PROFILE (
    studentID     VARCHAR(20)   NOT NULL PRIMARY KEY,
    name          VARCHAR(100)  NOT NULL,
    programme     VARCHAR(100)  NOT NULL,
    email         VARCHAR(100)  NOT NULL,
    hobbies       VARCHAR(255),
    introduction  VARCHAR(500)
);

-- Sample data (optional) - remove or edit as you like.
INSERT INTO PROFILE (studentID, name, programme, email, hobbies, introduction)
VALUES ('2024123456', 'Ahmad Daniel', 'Bachelor of Computer Science',
        'ahmad.daniel@uitm.edu.my', 'Gaming, Coding, Football',
        'A passionate computer science student who loves building web applications and exploring new technologies.');

INSERT INTO PROFILE (studentID, name, programme, email, hobbies, introduction)
VALUES ('2024654321', 'Nur Aisyah', 'Bachelor of Information Technology',
        'nur.aisyah@uitm.edu.my', 'Reading, Photography, Badminton',
        'An enthusiastic IT student interested in UI/UX design and mobile app development.');

INSERT INTO PROFILE (studentID, name, programme, email, hobbies, introduction)
VALUES ('2024998877', 'Lee Wei Jian', 'Bachelor of Computer Science',
        'lee.weijian@uitm.edu.my', 'Cycling, Reading, Chess',
        'A curious learner who enjoys solving algorithmic problems and competitive programming.');

-- Quick check - view all inserted records
SELECT * FROM PROFILE;
