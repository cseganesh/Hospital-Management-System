package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentBooking extends JFrame implements ActionListener {

    // Form fields
    JComboBox<String> departmentComboBox, doctorComboBox, dateComboBox, timeComboBox, paymentModeComboBox;
    JTextField consultantFeeField;
    JButton bookButton, paymentButton, backButton;

    // Database connection placeholder
    Conn conn = new Conn();

    public AppointmentBooking() {
        // Frame setup
        setTitle("Appointment Booking");
        setLayout(null);

        // Setting up the background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/2.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);

        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 500, 500);
        add(backgroundLabel);

        // Set background color
        getContentPane().setBackground(new Color(255, 255, 240));

        // Department dropdown
        JLabel departmentLabel = new JLabel("Select Department:");
        departmentLabel.setBounds(30, 30, 150, 30);
        departmentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        departmentLabel.setForeground(Color.GREEN); // Ensure visibility
        backgroundLabel.add(departmentLabel);

        departmentComboBox = new JComboBox<>(new String[]{"Cardiology", "Neurology", "Orthopedics"});
        departmentComboBox.setBounds(180, 30, 150, 30);
        backgroundLabel.add(departmentComboBox);

        // Doctor dropdown
        JLabel doctorLabel = new JLabel("Select Doctor Name:");
        doctorLabel.setBounds(30, 80, 150, 30);
        doctorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        doctorLabel.setForeground(Color.GREEN); // Ensure visibility
        backgroundLabel.add(doctorLabel);

        doctorComboBox = new JComboBox<>(new String[]{"Dr. Smith", "Dr. John", "Dr. Lee"});
        doctorComboBox.setBounds(180, 80, 150, 30);
        backgroundLabel.add(doctorComboBox);

        // Consultant Fee
        JLabel feeLabel = new JLabel("Consultant Fee:");
        feeLabel.setBounds(30, 130, 150, 30);
        feeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        feeLabel.setForeground(Color.GREEN); // Ensure visibility
        backgroundLabel.add(feeLabel);

        consultantFeeField = new JTextField();
        consultantFeeField.setBounds(180, 130, 150, 30);
        backgroundLabel.add(consultantFeeField);

        // Date dropdown
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(30, 180, 150, 30);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setForeground(Color.GREEN); // Ensure visibility
        backgroundLabel.add(dateLabel);

        dateComboBox = new JComboBox<>(new String[]{"2024-09-24", "2024-09-25", "2024-09-26"});
        dateComboBox.setBounds(180, 180, 150, 30);
        backgroundLabel.add(dateComboBox);

        // Time dropdown
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(30, 230, 150, 30);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setForeground(Color.GREEN); // Ensure visibility
        backgroundLabel.add(timeLabel);

        timeComboBox = new JComboBox<>(new String[]{"10:00 AM", "11:00 AM", "12:00 PM"});
        timeComboBox.setBounds(180, 230, 150, 30);
        backgroundLabel.add(timeComboBox);

        // Payment Mode dropdown
        JLabel paymentLabel = new JLabel("Payment Mode:");
        paymentLabel.setBounds(30, 280, 150, 30);
        paymentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        paymentLabel.setForeground(Color.GREEN); // Ensure visibility
        backgroundLabel.add(paymentLabel);

        paymentModeComboBox = new JComboBox<>(new String[]{"Cash", "Card", "Net Banking"});
        paymentModeComboBox.setBounds(180, 280, 150, 30);
        backgroundLabel.add(paymentModeComboBox);

        // Buttons
        bookButton = new JButton("Book Appointment");
        bookButton.setBounds(30, 330, 150, 30);
        bookButton.setBackground(new Color(135, 206, 250));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.setFocusPainted(false);
        bookButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        bookButton.addActionListener(this);
        backgroundLabel.add(bookButton);

        paymentButton = new JButton("Online Payment");
        paymentButton.setBounds(180, 330, 150, 30);
        paymentButton.setBackground(new Color(144, 238, 144));
        paymentButton.setForeground(Color.WHITE);
        paymentButton.setFont(new Font("Arial", Font.BOLD, 14));
        paymentButton.setFocusPainted(false);
        paymentButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        paymentButton.addActionListener(this);
        backgroundLabel.add(paymentButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(115, 380, 150, 30);
        backButton.setBackground(new Color(255, 165, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        // Frame configuration
        setSize(400, 500);
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String department = departmentComboBox.getSelectedItem().toString();
        String doctorName = doctorComboBox.getSelectedItem().toString();
        String consultantFee = consultantFeeField.getText();
        String date = dateComboBox.getSelectedItem().toString();
        String time = timeComboBox.getSelectedItem().toString();
        String paymentMode = paymentModeComboBox.getSelectedItem().toString();
        int paymentModeCode = paymentMode.equals("Cash") ? 1 : paymentMode.equals("Card") ? 2 : 3;

        if (e.getSource() == bookButton) {
            // Code to handle booking the appointment
            bookAppointment(department, doctorName, consultantFee, date, time, paymentModeCode);
        } else if (e.getSource() == paymentButton) {
            // Validate the consultant fee before proceeding
            try {
                double feeValue = Double.parseDouble(consultantFee);
                setVisible(false); // Hide current window
                new Payment(1, feeValue); // Pass the appointment ID and fee to Payment
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid consultant fee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            // Navigate back to Login screen
            dispose(); // Close the current window
            new Login(); // Open the Login screen
        }
    }

    private void bookAppointment(String department, String doctorName, String fee, String date, String time, int paymentMode) {
        try {
            // Convert the time to a 24-hour format
            SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
            Date parsedTime = inputFormat.parse(time);
            String formattedTime = outputFormat.format(parsedTime);

            String sql = "INSERT INTO appointment (department_id, doctor_name, date, time, consultant_fee, payment_mode, status) VALUES (?, ?, ?, ?, ?, ?, 'CONFIRM')";
            PreparedStatement pstmt = conn.c.prepareStatement(sql);
            pstmt.setInt(1, getDepartmentId(department));
            pstmt.setString(2, doctorName);
            pstmt.setString(3, date);
            pstmt.setString(4, formattedTime); // Use the formatted time here
            pstmt.setString(5, fee);
            pstmt.setInt(6, paymentMode);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book appointment.");
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid time format.");
            e.printStackTrace();
        }
    }

    private int getDepartmentId(String department) {
        // Simulating department ID retrieval, this could be fetched from a database table
        switch (department) {
            case "Cardiology":
                return 1;
            case "Neurology":
                return 2;
            case "Orthopedics":
                return 3;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new AppointmentBooking();
    }
}
