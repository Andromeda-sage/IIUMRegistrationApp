package com.mycompany.iiumregistrationapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List; // Keep this import as it might be used in other classes

public class App extends Application {

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Lecturer> lect = new ArrayList<>();
    private Stage primaryStage;

    // Enum to represent user roles
    public enum UserRole {
        ADMIN, STUDENT, LECTURER
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        // Initialize some dummy data for testing
        initializeData();
        showLoginScreen();
    }

    private void initializeData() {
        // Add some dummy students
        students.add(new Student("12345", "Alice", "Smith", "012-3456789"));
        students.add(new Student("67890", "Bob", "Johnson", "019-8765432"));

        // Add some dummy courses
        courses.add(new Course("CSC101", "Introduction to Programming", 3));
        courses.add(new Course("MTH201", "Calculus I", 4));
        
        // Add some dummy lecturers
        lect.add(new Lecturer("10567", "Dr.", "Dini Oktarina", "0198765432"));
        lect.add(new Lecturer("10000", "Dr.", "Wahab", "014823923"));
    }

    private void showLoginScreen() {
        VBox loginBox = new VBox(15);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setStyle("-fx-padding: 30; -fx-background-color: #f0f8ff;");

        // Add IIUM Logo
        ImageView logoImageView = new ImageView();
        try {
            Image logo = new Image("https://th.bing.com/th/id/OIP.zHLG-OYp8yKrjnYyBpi1UwHaB3?rs=1&pid=ImgDetMain");
            logoImageView.setImage(logo);
            logoImageView.setFitWidth(200);
            logoImageView.setFitHeight(80);
            logoImageView.setPreserveRatio(true);
            logoImageView.setSmooth(true);
        } catch (Exception e) {
            System.out.println("Could not load IIUM logo image: " + e.getMessage());
            // Optionally, set a placeholder image or display a text message
            Label placeholder = new Label("IIUM Logo");
            placeholder.setStyle("-fx-font-size: 24px; -fx-text-fill: #ccc;");
            loginBox.getChildren().add(0, placeholder); // Add placeholder if image fails
        }

        // Title
        Label titleLabel = new Label("IIUM Course Registration System");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c5aa0;");

        // Login form
        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(300);
        formBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        Label userLabel = new Label("Username:");
        userLabel.setStyle("-fx-font-weight: bold;");
        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-padding: 8;");
        
        Label passLabel = new Label("Password:");
        passLabel.setStyle("-fx-font-weight: bold;");
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-padding: 8;");

        // New: Role selection ComboBox
        Label roleLabel = new Label("Login As:");
        roleLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Student", "Lecturer");
        roleComboBox.setValue("Admin"); // Default selection
        roleComboBox.setStyle("-fx-padding: 8; -fx-pref-width: 200;");


        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #2c5aa0; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-weight: bold;");
        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle("-fx-background-color: #1e3f73; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-weight: bold;"));
        loginBtn.setOnMouseExited(e -> loginBtn.setStyle("-fx-background-color: #2c5aa0; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-weight: bold;"));
        
        Label message = new Label();
        message.setStyle("-fx-text-fill: red;");

        loginBtn.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();
            String selectedRole = roleComboBox.getValue(); // Get selected role

            boolean authenticated = false;
            UserRole userRole = null;

            if (selectedRole != null) {
                switch (selectedRole) {
                    case "Admin":
                        if (user.equals("admin") && pass.equals("1234")) {
                            authenticated = true;
                            userRole = UserRole.ADMIN;
                        }
                        break;
                    case "Student":
                        if (user.equals("student") && pass.equals("s123")) {
                            authenticated = true;
                            userRole = UserRole.STUDENT;
                        }
                        break;
                    case "Lecturer":
                        if (user.equals("lecturer") && pass.equals("l123")) {
                            authenticated = true;
                            userRole = UserRole.LECTURER;
                        }
                        break;
                }
            }

            if (authenticated) {
                // Pass the authenticated role to the main application
                showMainApp(userRole); 
            } else {
                message.setText("Invalid " + (selectedRole != null ? selectedRole : "login") + " credentials.");
            }
        });

        formBox.getChildren().addAll(
            userLabel, usernameField,
            passLabel, passwordField,
            roleLabel, roleComboBox, // Add role selection
            loginBtn, message
        );

        // Add logoImageView only if it was successfully loaded, otherwise the placeholder is already there
        if (logoImageView.getImage() != null) {
            loginBox.getChildren().addAll(logoImageView, titleLabel, formBox);
        } else {
            loginBox.getChildren().addAll(titleLabel, formBox); // If logo failed, just add title and form
        }

        Scene loginScene = new Scene(loginBox, 450, 550); // Increased height to accommodate new field
        primaryStage.setTitle("IIUM Course Registration - Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Modified showMainApp to accept UserRole and include a Logout button
    private void showMainApp(UserRole role) {
        TabPane tabPane = new TabPane();

        // Admin can see all tabs
        if (role == UserRole.ADMIN) {
            Tab studentTab = new Tab("Students", studentTabUI());
            Tab lectTab = new Tab("Lecturer", lectTabUI());
            Tab courseTab = new Tab("Courses", courseTabUI());
            Tab registrationTab = new Tab("Registrations", registrationTabUI());
            Tab assignTab = new Tab("Assign", assignTabUI());
            tabPane.getTabs().addAll(studentTab, lectTab, courseTab, registrationTab, assignTab);
        } else if (role == UserRole.STUDENT) {
            // Students might only see a registration tab and a view of their courses
            Tab registrationTab = new Tab("Register/View Courses", studentRegistrationViewUI());
            tabPane.getTabs().addAll(registrationTab);
        } else if (role == UserRole.LECTURER) {
            // Lecturers might see course management or student lists for their courses
            Tab lecturerTab = new Tab("My Courses (Lecturer)", lecturerViewUI());
            Tab detailTab = new Tab("Detail", detailTab());
            tabPane.getTabs().addAll(lecturerTab, detailTab);
        }

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // --- Logout Button ---
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-padding: 8 15; -fx-font-weight: bold;");
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle("-fx-background-color: #b71c1c; -fx-text-fill: white; -fx-padding: 8 15; -fx-font-weight: bold;"));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-padding: 8 15; -fx-font-weight: bold;"));
        logoutButton.setOnAction(e -> showLoginScreen()); // Go back to login screen

        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: #e0e0e0;");
        topBar.getChildren().addAll(new Label("Logged in as: " + role.name()), logoutButton);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topBar);
        mainLayout.setCenter(tabPane);

        Scene scene = new Scene(mainLayout, 600, 450); // Increased height to accommodate the top bar
        primaryStage.setTitle("IIUM Course Registration System - " + role.name());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox studentTabUI() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        TextField studIdField = new TextField();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField phoneField = new TextField();
        TextArea output = new TextArea();
        Button addBtn = new Button("Add Student");

        studIdField.setPromptText("Student ID");
        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        phoneField.setPromptText("Phone Number");
        output.setEditable(false);

        addBtn.setOnAction(e -> {
            String id = studIdField.getText();
            String first = firstNameField.getText();
            String last = lastNameField.getText();
            String phone = phoneField.getText();

            if (id.isEmpty() || first.isEmpty() || last.isEmpty()) {
                output.setText("Please fill all required fields (Student ID, First Name, Last Name).");
            } else {
                Student s = new Student(id, first, last, phone);
                students.add(s);
                output.setText("Student added:\n" + first + " " + last + " (" + id + ")");
                studIdField.clear(); firstNameField.clear(); lastNameField.clear(); phoneField.clear();
            }
        });

        vbox.getChildren().addAll(studIdField, firstNameField, lastNameField, phoneField, addBtn, output);
        return vbox;
    }
    
    private VBox lectTabUI() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        TextField lectIdField = new TextField();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField phoneField = new TextField();
        TextArea output = new TextArea();
        Button addBtn = new Button("Add Lecturer");

        lectIdField.setPromptText("Lecturer ID");
        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        phoneField.setPromptText("Phone Number");
        output.setEditable(false);

        addBtn.setOnAction(e -> {
            String id = lectIdField.getText();
            String first = firstNameField.getText();
            String last = lastNameField.getText();
            String phone = phoneField.getText();

            if (id.isEmpty() || first.isEmpty() || last.isEmpty()) {
                output.setText("Please fill all required fields (Lecturer ID, First Name, Last Name).");
            } else {
                Lecturer l = new Lecturer(id, first, last, phone);
                lect.add(l);
                output.setText("Lecturer added:\n" + first + " " + last + " (" + id + ")");
                lectIdField.clear(); firstNameField.clear(); lastNameField.clear(); phoneField.clear();
            }
        });

        vbox.getChildren().addAll(lectIdField, firstNameField, lastNameField, phoneField, addBtn, output);
        return vbox;
    }

    private VBox courseTabUI() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        TextField courseCodeField = new TextField();
        TextField courseNameField = new TextField();
        TextField chrField = new TextField();
        TextArea output = new TextArea();
        Button addBtn = new Button("Add Course");

        courseCodeField.setPromptText("Course Code");
        courseNameField.setPromptText("Course Name");
        chrField.setPromptText("Credit Hours");
        output.setEditable(false);

        addBtn.setOnAction(e -> {
            try {
                String code = courseCodeField.getText();
                String name = courseNameField.getText();
                int chr = Integer.parseInt(chrField.getText());

                if (code.isEmpty() || name.isEmpty() || chrField.getText().isEmpty()) {
                    output.setText("Please fill all required fields (Course Code, Course Name, Credit Hours).");
                    return;
                }

                Course c = new Course(code, name, chr);
                courses.add(c);
                // c.addCourse(); // This method is part of the Course class now, but not directly accessible like this.
                                // If you have a static method addCourse in Course class, you would call Course.addCourse(c);
                                // For now, simply adding to the 'courses' ArrayList is sufficient for this application's scope.
                output.setText("Course added:\n" + code + " - " + name + " (" + chr + " CHR)");
                courseCodeField.clear(); courseNameField.clear(); chrField.clear();
            } catch (NumberFormatException ex) {
                output.setText("Credit Hours must be an integer.");
            }
        });

        vbox.getChildren().addAll(courseCodeField, courseNameField, chrField, addBtn, output);
        return vbox;
    }

    private VBox registrationTabUI() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        TextField regIdField = new TextField();
        TextField studIdField = new TextField();
        TextField courseCodeField = new TextField();
        TextField dateField = new TextField();
        TextArea output = new TextArea();
        Button confirmBtn = new Button("Confirm Registration");
        Button cancelBtn = new Button("Cancel Registration");

        regIdField.setPromptText("Registration ID");
        studIdField.setPromptText("Student ID");
        courseCodeField.setPromptText("Course Code");
        dateField.setPromptText("Registration Date (YYYY-MM-DD)");
        output.setEditable(false);

        Registration[] currentReg = new Registration[1]; // Using an array to hold a mutable reference

        confirmBtn.setOnAction(e -> {
            String regId = regIdField.getText();
            String sid = studIdField.getText();
            String ccode = courseCodeField.getText();
            String date = dateField.getText();

            if (regId.isEmpty() || sid.isEmpty() || ccode.isEmpty() || date.isEmpty()) {
                output.setText("Please fill all required fields for registration.");
                return;
            }

            // Check if student and course exist
            Student foundStudent = null;
            for (Student s : students) {
                if (s.getStudID().equals(sid)) {
                    foundStudent = s;
                    break;
                }
            }

            Course foundCourse = null;
            for (Course c : courses) {
                if (c.getCourseCode().equals(ccode)) {
                    foundCourse = c;
                    break;
                }
            }

            if (foundStudent == null) {
                output.setText("Error: Student with ID " + sid + " not found.");
                return;
            }
            if (foundCourse == null) {
                output.setText("Error: Course with code " + ccode + " not found.");
                return;
            }

            // Check if student is already registered for the course
            if (foundStudent.getRegisteredCourses().contains(ccode)) {
                output.setText("Student " + sid + " is already registered for course " + ccode + ".");
                return;
            }

            Registration reg = new Registration(regId, sid, ccode, date);
            reg.confirmRegistration();
            currentReg[0] = reg; // Store the current registration

            foundStudent.registerCourse(ccode); // Register the course for the found student

            output.setText("Registration confirmed:\nRegID: " + regId + ", Student: " + sid + ", Course: " + ccode);
            regIdField.clear(); studIdField.clear(); courseCodeField.clear(); dateField.clear();
        });

        cancelBtn.setOnAction(e -> {
            if (currentReg[0] != null) {
                currentReg[0].cancelRegistration();
                // Find the student and drop the course
                for (Student s : students) {
                    if (s.getStudID().equals(currentReg[0].getStudID())) {
                        s.dropCourse(currentReg[0].getCourseCode());
                        break; // Student found and course dropped, exit loop
                    }
                }
                output.setText("Registration cancelled for RegID: " + currentReg[0].getRegID() + ".");
                currentReg[0] = null; // Clear the current registration
            } else {
                output.setText("No active registration to cancel.");
            }
        });

        vbox.getChildren().addAll(regIdField, studIdField, courseCodeField, dateField, confirmBtn, cancelBtn, output);
        return vbox;
    }
    
private VBox assignTabUI() {
    VBox vbox = new VBox(10);
    vbox.setStyle("-fx-padding: 10;");

    TextField assIdField = new TextField();
    TextField lectIdField = new TextField();
    TextField courseCodeField = new TextField();
    TextField dateField = new TextField();
    TextArea output = new TextArea();
    Button confirmBtn = new Button("Confirm Assignment");
    Button cancelBtn = new Button("Cancel Assignment");

    assIdField.setPromptText("Assignment ID");
    lectIdField.setPromptText("Lecturer ID");
    courseCodeField.setPromptText("Course Code");
    dateField.setPromptText("Assignment Date (YYYY-MM-DD)");
    output.setEditable(false);

    Assignation[] currentAss = new Assignation[1]; // Store the current assignment

    confirmBtn.setOnAction(e -> {
        String assId = assIdField.getText();
        String lid = lectIdField.getText();
        String ccode = courseCodeField.getText();
        String date = dateField.getText();

        if (assId.isEmpty() || lid.isEmpty() || ccode.isEmpty() || date.isEmpty()) {
            output.setText("Please fill all required fields for assignment.");
            return;
        }

        // Find Lecturer
        Lecturer foundLecturer = null;
        for (Lecturer l : lect) {
            if (l.getLectID().equals(lid)) {
                foundLecturer = l;
                break;
            }
        }

        // Find Course
        Course foundCourse = null;
        for (Course c : courses) {
            if (c.getCourseCode().equals(ccode)) {
                foundCourse = c;
                break;
            }
        }

        if (foundLecturer == null) {
            output.setText("Error: Lecturer with ID " + lid + " not found.");
            return;
        }

        if (foundCourse == null) {
            output.setText("Error: Course with code " + ccode + " not found.");
            return;
        }

        // Check if course is already assigned to lecturer
        if (foundLecturer.getAssignedCourses().contains(foundCourse)) {
            output.setText("Lecturer " + lid + " is already assigned to course " + ccode + ".");
            return;
        }

        Assignation ass = new Assignation(assId, lid, ccode, date);
        ass.confirmAssignation();
        currentAss[0] = ass;

        foundLecturer.assignCourse(foundCourse); // Method you define in Lecturer class

        output.setText("Assignment confirmed:\nAssignment ID: " + assId +
                       "\nLecturer: " + lid + "\nCourse: " + ccode + "\nDate: " + date);
        assIdField.clear(); lectIdField.clear(); courseCodeField.clear(); dateField.clear();
    });

    cancelBtn.setOnAction(e -> {
        if (currentAss[0] != null) {
            currentAss[0].cancelAssignation();

            String lid = currentAss[0].getLectID();
            String ccode = currentAss[0].getCourseCode();

            // Remove course assignment
            for (Lecturer l : lect) {
                if (l.getLectID().equals(lid)) {
                    l.getAssignedCourses().removeIf(c -> {
                        return c.getCourseCode().equals(ccode);
                    });
                    break;
                }
            }

            output.setText("Assignment cancelled for Assignment ID: " + currentAss[0].getAssID());
            currentAss[0] = null;
        } else {
            output.setText("No active assignment to cancel.");
        }
    });

    vbox.getChildren().addAll(
        assIdField, lectIdField, courseCodeField, dateField,
        confirmBtn, cancelBtn, output
    );
    return vbox;
}



    // New UI for Student role
    private VBox studentRegistrationViewUI() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        Label title = new Label("Student Course Registration");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField studentIdInput = new TextField();
        studentIdInput.setPromptText("Your Student ID");
        TextField courseCodeInput = new TextField();
        courseCodeInput.setPromptText("Course Code to Register");
        Button registerCourseBtn = new Button("Register for Course");
        Button viewRegisteredCoursesBtn = new Button("View My Registered Courses");
        TextArea studentOutput = new TextArea();
        studentOutput.setEditable(false);
        studentOutput.setPromptText("Registration messages and course list will appear here.");

        registerCourseBtn.setOnAction(e -> {
            String studentID = studentIdInput.getText();
            String courseCode = courseCodeInput.getText();

            if (studentID.isEmpty() || courseCode.isEmpty()) {
                studentOutput.setText("Please enter your Student ID and Course Code.");
                return;
            }

            // Find the student
            Student foundStudent = null;
            for (Student s : students) {
                if (s.getStudID().equals(studentID)) {
                    foundStudent = s;
                    break;
                }
            }

            // Find the course
            Course foundCourse = null;
            for (Course c : courses) {
                if (c.getCourseCode().equals(courseCode)) {
                    foundCourse = c;
                    break;
                }
            }

            if (foundStudent != null && foundCourse != null) {
                // Check if student is already registered for the course
                if (foundStudent.getRegisteredCourses().contains(courseCode)) {
                    studentOutput.setText("You are already registered for " + courseCode + ".");
                } else {
                    foundStudent.registerCourse(courseCode);
                    studentOutput.setText("Successfully registered student " + studentID + " for " + courseCode + ".");
                    studentIdInput.clear();
                    courseCodeInput.clear();
                }
            } else {
                studentOutput.setText("Error: Student ID or Course Code not found.");
            }
        });

        viewRegisteredCoursesBtn.setOnAction(e -> {
            String studentID = studentIdInput.getText();
            if (studentID.isEmpty()) {
                studentOutput.setText("Please enter your Student ID to view registered courses.");
                return;
            }

            Student foundStudent = null;
            for (Student s : students) {
                if (s.getStudID().equals(studentID)) {
                    foundStudent = s;
                    break;
                }
            }

            if (foundStudent != null) {
                List<String> registeredCourses = foundStudent.getRegisteredCourses();
                if (registeredCourses.isEmpty()) {
                    studentOutput.setText("Student " + studentID + " has no registered courses.");
                } else {
                    StringBuilder sb = new StringBuilder("Courses registered by " + studentID + ":\n");
                    for (String courseCode : registeredCourses) {
                        sb.append("- ").append(courseCode).append("\n");
                    }
                    studentOutput.setText(sb.toString());
                }
            } else {
                studentOutput.setText("Error: Student with ID " + studentID + " not found.");
            }
        });

        vbox.getChildren().addAll(title, studentIdInput, courseCodeInput, registerCourseBtn, viewRegisteredCoursesBtn, studentOutput);
        return vbox;
    }

    // New UI for Lecturer role (placeholder)
    private VBox lecturerViewUI() {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        Label title = new Label("Lecturer Dashboard");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label info = new Label("This section would display courses taught by the lecturer, enrolled students, etc.");
        
        // Example of displaying all courses
        TextArea coursesList = new TextArea();
        coursesList.setEditable(false);
        coursesList.setPromptText("List of all courses available.");
        StringBuilder sb = new StringBuilder("All Available Courses:\n");
        for (Course c : courses) {
            sb.append(c.getCourseCode()).append(" - ").append(c.getCourseName()).append(" (").append(c.getCreditHours()).append(" CHR)\n");
        }
        coursesList.setText(sb.toString());

        vbox.getChildren().addAll(title, info, coursesList);
        return vbox;
    }
    
    private VBox detailTab() {
    VBox vbox = new VBox(10);
    vbox.setStyle("-fx-padding: 10;");

    Label title = new Label("Lecturer Course & Student Viewer");
    TextField lectIdInput = new TextField();
    lectIdInput.setPromptText("Enter Your Lecturer ID");

    Button viewCoursesBtn = new Button("View My Courses");
    TextArea lectOutput = new TextArea();
    lectOutput.setEditable(false);
    lectOutput.setPromptText("Your courses and enrolled students will appear here.");

    viewCoursesBtn.setOnAction(e -> {
        String lecturerID = lectIdInput.getText();
        if (lecturerID.isEmpty()) {
            lectOutput.setText("Please enter your Lecturer ID.");
            return;
        }

        Lecturer foundLecturer = null;
        for (Lecturer l : lect) { // Make sure 'lecturers' list is defined in your class
            if (l.getLectID().equals(lecturerID)) {
                foundLecturer = l;
                break;
            }
        }

        if (foundLecturer != null) {
            List<Course> assignedCourses = foundLecturer.getAssignedCourses(); // adjust if using a different name
            if (assignedCourses.isEmpty()) {
                lectOutput.setText("You are not assigned to any courses.");
            }
        } else {
            lectOutput.setText("Lecturer with ID " + lecturerID + " not found.");
        }
    });

    vbox.getChildren().addAll(title, lectIdInput, viewCoursesBtn, lectOutput);
    return vbox;
}
        
}