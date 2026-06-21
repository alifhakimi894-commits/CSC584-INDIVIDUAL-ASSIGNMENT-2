-- =====================================================================
-- database.sql
-- Profile Management System - CSC584 Individual Assignment 2
-- Database  : StudentProfilesDB
-- Engine    : Apache Derby (NetBeans "Java DB")
-- =====================================================================
--       Database Name : StudentProfilesDB
--       User Name     : student
--       Password      : student123

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
