
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

![UseCaseDiagram1](https://github.com/user-attachments/assets/394d2581-c805-4517-8e17-bc8b0202e346)


---


## Screenshots

Here are some screenshots of the system:
 ## Home Page
![Screenshot 2024-09-23 110417](https://github.com/user-attachments/assets/fbcbf601-83d4-489d-b79b-630514c4071d)


## Login Page
![Screenshot 2024-09-23 111130](https://github.com/user-attachments/assets/8e76f2da-6f93-4e11-9180-bf987b817ecf)



## Admin Page
![Screenshot 2024-09-23 111907](https://github.com/user-attachments/assets/5c5dd8ae-09aa-4078-95c2-276dc84420e4)



## AdminDashboard Page
![Screenshot 2024-09-23 112218](https://github.com/user-attachments/assets/51f71f31-9815-48b1-9120-e6b2475a3806)



## Add Doctor 
![Screenshot 2024-09-23 112921](https://github.com/user-attachments/assets/efca2725-82e7-40dd-81ae-ede673e9c5a3)



## Doctor Login Page
![Screenshot 2024-09-23 113442](https://github.com/user-attachments/assets/5951df9c-4d65-4c85-932f-d1753f724d0c)


## Doctor Dashboard Page
![Screenshot 2024-09-23 113850](https://github.com/user-attachments/assets/cd1ce590-964d-4a96-af23-4a27b28c68af)



## View Doctor Page
![Screenshot 2024-09-23 114618](https://github.com/user-attachments/assets/b13ec8ae-56d3-41c3-9796-82e0754c05a3)



## Register Page
![Screenshot 2024-09-23 115728](https://github.com/user-attachments/assets/9b57a05f-64c7-4244-a5c3-0101134a1ab2)



## Patient Page
![Screenshot 2024-09-23 120423](https://github.com/user-attachments/assets/dde4120f-784a-49a3-b271-e1c5ff6b17d8)



## Patient Login Page
![Screenshot 2024-09-23 120703](https://github.com/user-attachments/assets/661eabfd-0351-468b-aa01-11d8953d361e)


## Patient Dashboard Page
![Screenshot 2024-09-23 121030](https://github.com/user-attachments/assets/e6daf8f3-0b57-4293-bf32-6ca0710db671)



## View Patient Page
![Screenshot 2024-09-23 121549](https://github.com/user-attachments/assets/3f6987f4-a7ed-48a4-bdfa-afe23f045693)



## Payment Page
![Screenshot 2024-09-23 121944](https://github.com/user-attachments/assets/c27f82c7-298a-48b7-86a4-ed2b06b82761)




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
    git clone https://github.com/cseganesh/Hospital-Management-System
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
