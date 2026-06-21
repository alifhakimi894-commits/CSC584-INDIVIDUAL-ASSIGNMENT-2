# CSC584 - Individual Assignment 2

## UiTM Profile Management System

**Course:** CSC584 – Individual Assignment 2

---

## Student Information

* **Student Name:** MUHAMMAD ALIF HAKIMI BIN MOHD FAUZI
* **Student ID:** 2024405276
* **Programme:** CDCS266: Information System Engineering
* **Course:** CSC584

---

## 1. Project Description

This project enhances the simple Profile Application built in Assignment 1 (HTML + Servlet + JSP) into a full **Profile Management System** that stores and manages student profile information in a database, following the **MVC (Model–View–Controller)** architecture.

### MVC Architecture

| Component  | Implementation                                                                  |
| ---------- | ------------------------------------------------------------------------------- |
| Model      | `ProfileBean.java` (JavaBean)                                                   |
| View       | `index.html`, `profile.jsp`, `viewProfiles.jsp`, `editProfile.jsp`, `error.jsp` |
| Controller | `ProfileServlet.java`                                                           |

Data is persisted using **JDBC** into a **NetBeans Java DB (Apache Derby)** database:

* **Database Name:** `StudentProfilesDB`
* **Table Name:** `PROFILE`

---

## 2. List of Implemented Features

### Core Requirements

* HTML registration form (`index.html`)
* `ProfileBean` JavaBean (Model) with private fields + getters/setters
* `ProfileServlet` (Controller) handling POST form submission
* JDBC integration (`ProfileDAO` + `DBConnection`) for:

  * INSERT
  * SELECT
  * UPDATE
  * DELETE
* `profile.jsp` — displays the submitted profile after saving
* `viewProfiles.jsp` — displays all saved profiles in a card layout

---

### Additional Features (All Implemented)

* **Option A – Search Profile:**
  Search by Student ID or Name (partial match)

* **Option B – Edit Profile:**
  Update an existing profile's details

* **Option C – Delete Profile:**
  Remove a profile record (with confirmation prompt)

* **Option D – Filter Profiles:**
  Filter profiles by Programme and/or Hobbies

---

### UI / UX Features

* Consistent **dark purple & gold "glassmorphism" theme** across all pages

  * Shared stylesheet: `web/css/style.css`
* Card-based profile directory with hover effects
* Success/error alert banners

  * Examples: *"Profile saved"*, *"Profile deleted"*
* Responsive layout for smaller screens

---

## 3. Screenshots

### Registration Form

![Registration Form](https://github.com/user-attachments/assets/52f7a126-b90b-414a-bd41-def67e8505da)

---

### Saved Profile

![Saved Profile](https://github.com/user-attachments/assets/ffec9cba-52bf-4ce9-974f-8dc4e7ac61b1)

---

### All Profiles – Card View

![All Profiles Card View](https://github.com/user-attachments/assets/dee89c0e-99fb-44f6-81cf-0cfbf091e312)

---

### Search Feature

![Search Feature](https://github.com/user-attachments/assets/93b2d3b6-e3ac-4172-92e5-779d7548fd3d)

---

### Filter Feature

![Filter Feature](https://github.com/user-attachments/assets/ff366ad5-4410-4938-b85f-031a2108e927)

---

### Edit Profile

![Edit Profile](https://github.com/user-attachments/assets/df776ead-ee72-44df-9c1e-3136f222c014)

---

### Delete Confirmation

![Delete Confirmation 1](https://github.com/user-attachments/assets/e735ef8d-f6ca-4712-8738-45704092a13e)

![Delete Confirmation 2](https://github.com/user-attachments/assets/f33efdfd-b585-474f-ada3-cd5d89d648c9)

---
