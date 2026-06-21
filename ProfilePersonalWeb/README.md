# UiTM Profile Management System
**CSC584 – Individual Assignment 2**

## Student Information
| Field | Details |
|---|---|
| **Student Name**  | _Your Name Here_ |
| **Student ID**    | _Your Student ID Here_ |
| **Programme**     | _Your Programme Here_ |
| **Course**        | CSC584 |

> ✏️ Replace the placeholders above with your real details before submitting.

---

## 1. Project Description

This project enhances the simple Profile Application built in Assignment 1
(HTML + Servlet + JSP) into a full **Profile Management System** that stores
and manages student profile information in a database, following the
**MVC (Model–View–Controller)** architecture:

| MVC Component | Implementation |
|---|---|
| **Model**      | `ProfileBean.java` (JavaBean) |
| **View**       | JSP pages (`index.html`, `profile.jsp`, `viewProfiles.jsp`, `editProfile.jsp`, `error.jsp`) |
| **Controller** | `ProfileServlet.java` |

Data is persisted using **JDBC** into a **NetBeans Java DB (Apache Derby)**
database named `StudentProfilesDB`, in a table named `PROFILE`.

---

## 2. Technology Stack

- HTML / CSS (custom glassmorphism styling)
- Java Servlet (`javax.servlet`, Servlet 3.x annotations)
- JSP + JSTL (`<c:...>` / `<fn:...>` tags)
- JavaBean (`ProfileBean`)
- JDBC (`java.sql.*`)
- **Database:** NetBeans Java DB (Apache Derby) — `StudentProfilesDB`
- Server: GlassFish / Apache Tomcat (any Servlet 3.x+ container)

---

## 3. List of Implemented Features

### Core Requirements
- ✅ HTML registration form (`index.html`)
- ✅ `ProfileBean` JavaBean (Model) with private fields + getters/setters
- ✅ `ProfileServlet` (Controller) handling POST form submission
- ✅ JDBC integration (`ProfileDAO` + `DBConnection`) for INSERT / SELECT / UPDATE / DELETE
- ✅ `profile.jsp` — displays the submitted profile after saving
- ✅ `viewProfiles.jsp` — displays all saved profiles in a **card layout**

### Additional Features (all 4 implemented)
- ✅ **Option A – Search Profile**: search by Student ID or Name (partial match)
- ✅ **Option B – Edit Profile**: update an existing profile's details
- ✅ **Option C – Delete Profile**: remove a profile record (with confirmation prompt)
- ✅ **Option D – Filter Profiles**: filter the list by Programme and/or Hobbies

### UI / UX
- Consistent dark purple & gold "glassmorphism" theme across every page
  (shared stylesheet at `web/css/style.css`)
- Card-based profile directory with hover effects
- Success/error alert banners (e.g. "Profile saved", "Profile deleted")
- Responsive layout for smaller screens

---

## 4. Project Structure

```
ProfilePersonalWeb/
├── database.sql                          # SQL script (run in NetBeans Java DB)
├── README.md
├── src/java/com/assignment/
│   ├── bean/
│   │   └── ProfileBean.java              # Model
│   ├── dao/
│   │   └── ProfileDAO.java               # JDBC data access (CRUD + search/filter)
│   ├── util/
│   │   └── DBConnection.java             # JDBC connection helper
│   └── servlet/
│       └── ProfileServlet.java           # Controller (front controller pattern)
└── web/
    ├── index.html                        # Registration form
    ├── profile.jsp                       # Single profile display (after save/edit/view)
    ├── viewProfiles.jsp                  # All profiles, card grid + search + filter
    ├── editProfile.jsp                   # Edit form (Option B)
    ├── error.jsp                         # Friendly error page
    └── css/style.css                     # Shared stylesheet (all pages)
```

---

## 5. Database Setup (NetBeans Java DB / Apache Derby)

This project is configured to use **NetBeans' built-in Java DB**, so no
external database server (MySQL/XAMPP) needs to be installed.

### Step 1 — Create the database in NetBeans
1. Open NetBeans → **Services** tab (`Window > Services`, or `Ctrl+5`).
2. Expand **Databases → Java DB**.
3. Right-click **Java DB → Create Database...**
   - Database Name: `StudentProfilesDB`
   - User Name: `admin`
   - Password: `admin`
   - Click **OK**.
4. NetBeans will start the Derby network server (default port `1527`) and
   create a connection automatically, shown under **Databases** as:
   `jdbc:derby://localhost:1527/StudentProfilesDB [admin on APP]`

### Step 2 — Run the SQL script
1. Right-click the new connection → **Connect** (if not already connected).
2. Right-click the connection again → **Execute Command...**
3. Open `database.sql` (from this repo), copy its contents into the SQL
   editor tab that opens, and click **Run SQL** (▶ or `F6`).
4. This creates the `PROFILE` table and inserts 3 sample records.

> If you ever need to reset the data, just re-run `database.sql` — the
> first line drops the existing table before recreating it.

### Step 3 — Add the Derby Client driver to the project
NetBeans normally does this automatically once you reference the Java DB
connection from your project, but if the project doesn't run because the
driver class can't be found:
1. Right-click the project → **Properties → Libraries**.
2. Under **Compile** (or **Packaging**), click **Add Library...**
3. Select **Java DB Driver** (provides `derbyclient.jar`).
4. Make sure "Package" is checked so the jar gets copied into
   `WEB-INF/lib` of the deployed WAR.

`DBConnection.java` connects using:
```
Driver   : org.apache.derby.jdbc.ClientDriver
URL      : jdbc:derby://localhost:1527/StudentProfilesDB
User     : admin
Password : admin
```
If you used a different username/password when creating the database in
NetBeans, update those three values in `DBConnection.java` to match.

---

## 6. How to Run the Project

1. Open the project folder in **NetBeans** (`File > Open Project`).
2. Complete the database setup above (Steps 1–3).
3. Make sure the Derby database server is **started** (NetBeans starts it
   automatically when you connect to it in the Services tab).
4. Right-click the project → **Run** (or `F6`).
5. The browser opens `index.html` — fill in the form and click
   **Save Profile**.
6. You'll be redirected to `profile.jsp` showing the saved record.
7. Click **View All Profiles** to see the full directory, search, filter,
   edit, or delete records.

---

## 7. Screenshots

> Add your screenshots to the `screenshots/` folder and reference them
> below before submitting (replace the placeholders).

| Page | Screenshot |
|---|---|
| Registration Form (`index.html`) | `screenshots/01-registration-form.png` |
| Saved Profile (`profile.jsp`) | `screenshots/02-profile-saved.png` |
| All Profiles – Card View (`viewProfiles.jsp`) | `screenshots/03-view-profiles.png` |
| Search Feature | `screenshots/04-search.png` |
| Filter Feature | `screenshots/05-filter.png` |
| Edit Profile (`editProfile.jsp`) | `screenshots/06-edit-profile.png` |
| Delete Confirmation | `screenshots/07-delete-confirm.png` |
| NetBeans Java DB Connection | `screenshots/08-netbeans-database.png` |

---

## 8. Notes

- Field names in the HTML form, `ProfileBean`, and the `PROFILE` table are
  kept consistent (`studentID`, `name`, `programme`, `email`, `hobbies`,
  `introduction`) to simplify the Servlet's parameter handling.
- `studentID` is the **primary key**; the system prevents duplicate IDs on
  insert and disables editing the ID field once a profile is created.
- All JDBC operations use `PreparedStatement` to prevent SQL injection.
