CREATE DATABASE hospital_db;

USE hospital_db;

CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);

INSERT INTO admin (username, password, name) 
VALUES ('gagan', 'securepassword', 'Admin Name');

SELECT * FROM admin;

CREATE TABLE doctors (
    doctor_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender ENUM('Male', 'Female'),
    specialization VARCHAR(100),
    experience VARCHAR(50),
    languages VARCHAR(100),
    mobile VARCHAR(15),
    email VARCHAR(100),
    monday_schedule VARCHAR(100),
    wednesday_schedule VARCHAR(100),
    friday_schedule VARCHAR(100),
    image_path VARCHAR(255)
);

ALTER TABLE doctors ADD COLUMN username VARCHAR(255), ADD COLUMN password VARCHAR(255);

DROP TABLE IF EXISTS doctors;
SELECT * FROM doctors;

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    gender VARCHAR(10),
    blood_group VARCHAR(5),
    email VARCHAR(100),
    mobile VARCHAR(15),
    address VARCHAR(255),
    private_cghs VARCHAR(10),
    image_path VARCHAR(255),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL
);


DROP TABLE IF EXISTS patients;
SELECT * FROM patients;

CREATE TABLE appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    department_id INT NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    consultant_fee DECIMAL(10, 2) NOT NULL,
    payment_mode INT NOT NULL,  -- 1 for Cash, 2 for Card, 3 for Net Banking
    status VARCHAR(10) NOT NULL  -- 'CONFIRM' or 'CANCEL'
);


DROP TABLE IF EXISTS appointment;
SELECT * FROM appointment;

CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- appointment_id INT NOT NULL,
    card_number VARCHAR(16) NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    expiry_date VARCHAR(15) NOT NULL,  -- Format: MM/YY
    cvv VARCHAR(3) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL  -- Using DECIMAL for better precision
    -- FOREIGN KEY (appointment_id) REFERENCES appointments(id)
);

DROP TABLE IF EXISTS payments;
SELECT * FROM payments;
