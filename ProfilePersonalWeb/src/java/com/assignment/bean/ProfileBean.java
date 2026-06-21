package com.assignment.bean;

/**
 * ProfileBean - JavaBean (Model component of MVC)
 *
 * Represents a single student profile record. Its private attributes
 * correspond directly to the columns of the PROFILE table in
 * StudentProfilesDB. Follows standard JavaBean conventions:
 * private fields, a no-arg constructor, a convenience constructor,
 * and public getter/setter methods for every field.
 */
public class ProfileBean implements java.io.Serializable {

    private String studentID;
    private String name;
    private String programme;
    private String email;
    private String hobbies;
    private String introduction;

    // No-argument constructor (required for JavaBean spec)
    public ProfileBean() {
    }

    // Convenience constructor - build a fully populated bean in one call
    public ProfileBean(String studentID, String name, String programme,
            String email, String hobbies, String introduction) {
        this.studentID = studentID;
        this.name = name;
        this.programme = programme;
        this.email = email;
        this.hobbies = hobbies;
        this.introduction = introduction;
    }

    // ----- Getters -----
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getProgramme() {
        return programme;
    }

    public String getEmail() {
        return email;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getIntroduction() {
        return introduction;
    }

    // ----- Setters -----
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ProfileBean{studentID=" + studentID + ", name=" + name
                + ", programme=" + programme + ", email=" + email
                + ", hobbies=" + hobbies + ", introduction=" + introduction + "}";
    }
}
