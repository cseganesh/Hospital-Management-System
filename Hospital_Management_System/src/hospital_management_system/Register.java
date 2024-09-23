package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Register extends JFrame implements ActionListener {
    JTextField nameField, dobField, emailField, mobileField, addressField;
    JComboBox<String> genderBox, bloodGroupBox, privateCghsBox;
    JButton browseButton, submitButton, patientLoginButton, backButton;
    JLabel uploadFilePath;
    String selectedImagePath = null; // This will hold the path of the selected image file

    public Register() {
        setTitle("Patient Registration Form");
        setLayout(null);

        // Load and scale the background image
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/icons/1.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 800, 600);
        add(backgroundLabel);

        JLabel titleLabel = new JLabel("PATIENT REGISTRATION FORM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 51, 102));
        titleLabel.setBounds(100, 20, 600, 50);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        backgroundLabel.add(titleLabel);

        String[] labels = {"NAME", "DOB", "GENDER", "BLOOD GROUP", "EMAIL", "MOBILE NO", "ADDRESS", "PRIVATE/CGHS"};
        int yPosition = 100;

        for (String label : labels) {
            JLabel formLabel = new JLabel(label + " : ");
            formLabel.setFont(new Font("Arial", Font.BOLD, 16));
            formLabel.setForeground(new Color(0, 51, 102));
            formLabel.setBounds(50, yPosition, 150, 30);
            backgroundLabel.add(formLabel);

            if (label.equals("GENDER")) {
                genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female", "Other"});
                genderBox.setBounds(200, yPosition, 250, 30);
                backgroundLabel.add(genderBox);
            } else if (label.equals("BLOOD GROUP")) {
                bloodGroupBox = new JComboBox<>(new String[]{"Select", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
                bloodGroupBox.setBounds(200, yPosition, 250, 30);
                backgroundLabel.add(bloodGroupBox);
            } else if (label.equals("PRIVATE/CGHS")) {
                privateCghsBox = new JComboBox<>(new String[]{"Select", "Private", "CGHS"});
                privateCghsBox.setBounds(200, yPosition, 250, 30);
                backgroundLabel.add(privateCghsBox);
            } else {
                JTextField textField = new JTextField();
                textField.setBounds(200, yPosition, 250, 30);
                backgroundLabel.add(textField);

                switch (label) {
                    case "NAME":
                        nameField = textField;
                        break;
                    case "DOB":
                        dobField = textField;
                        break;
                    case "EMAIL":
                        emailField = textField;
                        break;
                    case "MOBILE NO":
                        mobileField = textField;
                        break;
                    case "ADDRESS":
                        addressField = textField;
                        break;
                }
            }

            yPosition += 40;
        }

        JLabel uploadLabel = new JLabel("Upload patient image: ");
        uploadLabel.setBounds(50, yPosition, 150, 30);
        backgroundLabel.add(uploadLabel);

        browseButton = new JButton("Browse");
        browseButton.setBounds(200, yPosition, 100, 30);
        browseButton.addActionListener(this);
        backgroundLabel.add(browseButton);

        uploadFilePath = new JLabel();
        uploadFilePath.setBounds(310, yPosition, 300, 25);
        backgroundLabel.add(uploadFilePath);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(300, yPosition + 50, 150, 40);
        submitButton.addActionListener(this);
        backgroundLabel.add(submitButton);

        patientLoginButton = new JButton("Patient Login");
        patientLoginButton.setBounds(200, yPosition + 100, 150, 30);
        patientLoginButton.addActionListener(this);
        backgroundLabel.add(patientLoginButton);

        backButton = new JButton("Back");
        backButton.setBounds(400, yPosition + 100, 150, 30);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath(); // Save the selected file path
                uploadFilePath.setText(selectedImagePath); // Display the file path in the UI
            }
        } else if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String dob = dobField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String bloodGroup = (String) bloodGroupBox.getSelectedItem();
            String email = emailField.getText();
            String mobile = mobileField.getText();
            String address = addressField.getText();
            String privateCghs = (String) privateCghsBox.getSelectedItem();

            try {
                // Convert DOB to MySQL format (YYYY-MM-DD)
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dobFormatted = outputFormat.format(inputFormat.parse(dob));

                // Generate unique username and password
                String username = name.toLowerCase().replaceAll(" ", "") + "_" + UUID.randomUUID().toString().substring(0, 5);
                String password = UUID.randomUUID().toString().substring(0, 8);

                Conn conn = new Conn();
                String sql = "INSERT INTO patients (name, dob, gender, blood_group, email, mobile, address, private_cghs, username, password, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.c.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, dobFormatted);
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, bloodGroup);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, mobile);
                preparedStatement.setString(7, address);
                preparedStatement.setString(8, privateCghs);
                preparedStatement.setString(9, username);
                preparedStatement.setString(10, password);
                preparedStatement.setString(11, selectedImagePath); // Save the image path in the database
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration Completed!\nUsername: " + username + "\nPassword: " + password);

                // Clear all the input fields after successful submission
                nameField.setText("");
                dobField.setText("");
                genderBox.setSelectedIndex(0);
                bloodGroupBox.setSelectedIndex(0);
                emailField.setText("");
                mobileField.setText("");
                addressField.setText("");
                privateCghsBox.setSelectedIndex(0);
                uploadFilePath.setText(""); // Clear the file path
                selectedImagePath = null; // Reset image path after submission

            } catch (SQLException | ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in database operation.");
            }
        } else if (e.getSource() == patientLoginButton) {
            setVisible(false);
            new PatientLogin().setVisible(true);

        } else if (e.getSource() == backButton) {
            setVisible(false);
            new HomePage().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Register();
    }
}
