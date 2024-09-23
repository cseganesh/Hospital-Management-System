# Doctor Management System

## Project Overview
The **Doctor Management System** is designed to manage hospital operations such as doctor management, patient registration, appointment booking, and user login (admin, doctor, and patient). The system is built using Java Swing for the GUI, and MySQL for the database.

### Key Features:
- Admin, Doctor, and Patient login.
- Admin can manage doctors, view patient details, and handle appointments.
- Doctors can view their profiles, schedules, and manage appointments.
- Patients can register, book appointments, and view their appointment details.

---

## Technologies Used
- **Java (Swing)**: For building the user interface.
- **MySQL**: For database management.
- **JDBC**: For connecting the application to the database.
- **Prepared Statements**: For secure database interactions.
  
---

## Architecture Diagram
This system follows an **MVC (Model-View-Controller)** architecture pattern:
- **Model**: Represents the database and database queries (MySQL, JDBC).
- **View**: Java Swing forms for interacting with the user.
- **Controller**: Handles business logic and interaction between the View and Model.

---

## Use Case Diagram
The use case diagram for this system includes three primary actors:
1. **Admin**: Can manage doctors and view patient details.
2. **Doctor**: Can view schedules and appointments.
3. **Patient**: Can register, log in, and book appointments.

![UseCaseDiagram1](https://github.com/user-attachments/assets/b3407ad8-470a-478e-9191-97443d8a4b52)

---


## Screenshots

Here are some screenshots of the system:
 ## Home Page
![Screenshot 2024-09-23 110417](https://github.com/user-attachments/assets/0ac56ebb-e016-4ac1-8f38-053c79a96b60)

## Login Page
![Screenshot 2024-09-23 111130](https://github.com/user-attachments/assets/21df6780-725a-4950-bfe0-507df4a525c4)


## Admin Page
![Screenshot 2024-09-23 111907](https://github.com/user-attachments/assets/5463f75e-aad0-4a2f-9d28-48275b9fa322)


## AdminDashboard Page
![Screenshot 2024-09-23 112218](https://github.com/user-attachments/assets/38ffee5b-5e72-488d-b4b2-8166d8b5f7b2)


## Add Doctor 
![Screenshot 2024-09-23 112921](https://github.com/user-attachments/assets/ee5f147b-3e53-407a-886d-595d8ae4caf6)


## Doctor Login Page
![Screenshot 2024-09-23 113442](https://github.com/user-attachments/assets/79f134b0-da6e-4340-9f80-f2171e0e13ec)


## Doctor Dashboard Page
![Screenshot 2024-09-23 113850](https://github.com/user-attachments/assets/899dd9fa-2bea-4f32-9a34-6a82675b279f)


## View Doctor Page
![Screenshot 2024-09-23 114618](https://github.com/user-attachments/assets/3f8886dd-3f8c-41c3-ab4c-0cca0b32279d)


## Register Page
![Screenshot 2024-09-23 115728](https://github.com/user-attachments/assets/8c8d3e1b-e894-4209-a90e-ebaf0d8d63a8)


## Patient Page
![Screenshot 2024-09-23 120423](https://github.com/user-attachments/assets/6411c54d-41b4-4949-905e-29740310d21d)


## Patient Login Page
![Screenshot 2024-09-23 120703](https://github.com/user-attachments/assets/086d7e9a-d295-494e-bee3-5641acfc9510)


## Patient Dashboard Page
![Screenshot 2024-09-23 121030](https://github.com/user-attachments/assets/11ffdf2b-90bd-4395-8882-d48a447505a9)


## View Patient Page
![Screenshot 2024-09-23 121549](https://github.com/user-attachments/assets/b1397282-2cc7-44dd-88ab-e7e9558c7989)


## Payment Page
![Screenshot 2024-09-23 121944](https://github.com/user-attachments/assets/0015089a-ea43-4416-a3ca-1923749b3b07)



## System Requirements

### Software Requirements:
- **Java JDK 8+**: Required to run the Java Swing application.
- **MySQL 5.7+**: To manage the hospital's database (for doctors, patients, appointments).
- **JDBC Driver**: Required for connecting Java to MySQL.
- **IDE**: Eclipse/IntelliJ for development.
- **Operating System**: Windows, Linux, or macOS.
- **Additional Libraries:**
  - `mysql-connector-java`: For database connectivity.
  - `bcrypt`: For password encryption (optional).

### Hardware Requirements:
- **Processor**: 1.6 GHz or faster.
- **RAM**: 4 GB or higher.
- **Disk Space**: Minimum 5 GB of free space for the database and application.
- **Screen Resolution:** 1366x768 or higher.

---

## How to Run the Project

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-repo/DoctorManagementSystem.git
    ```

2. **Import the Project**:
    - Open your preferred IDE (Eclipse, IntelliJ) and import the project.

3. **Set up the MySQL Database**:
    - Create a MySQL database named `hospital`.
    - Import the SQL dump provided in the `database` folder.

4. **Configure Database Connection**:
    - Update the database credentials in `Conn.java`:
    ```java
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "password");
    ```

5. **Run the Application**:
    - Start the system by running the `HomePage.java` file.

---

## Conclusion

The Doctor Management System simplifies hospital operations by streamlining doctor management, patient registration, and appointment booking. It provides separate modules for admins, doctors, and patients, each tailored to their specific needs. The system is easy to use and maintain, and it ensures smooth interaction between users and the hospital’s data management.

Future developments could include more advanced features such as email/SMS notifications for appointments and integrating medical history management for patients, making it a more robust and comprehensive hospital management solution.

---

## Future Enhancements
- Add email and SMS notifications for appointment confirmations.
- Implement a search feature for patients to find doctors by specialization.
- Allow patients to view medical history and past appointments.

---

## Contributors
- **Your Name**: [Ganesh Kumar Mehta](https://github.com/cseganesh)
