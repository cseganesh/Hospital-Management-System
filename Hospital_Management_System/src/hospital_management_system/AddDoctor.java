package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;
import java.util.Random;

public class AddDoctor extends JFrame implements ActionListener {

    JTextField idField, nameField, ageField, specializationField, experienceField, languageField, mobileField, emailField;
    JButton addButton, deleteButton, uploadButton, BackButton;
    JTextField mondayField, wednesdayField, fridayField;
    JLabel imageLabel;
    String imagePath;
    JRadioButton maleButton, femaleButton;

    public AddDoctor() {
        // Frame settings
        setTitle("Add/Delete Doctor");
        setLayout(null);
        
        // Load and scale the background image to cover the full frame size
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/doctor_1.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800, 750, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 800, 750);
        add(backgroundLabel);
        
        // Left panel for doctor details
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBounds(20, 30, 320, 400);
        leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Doctor Details", 0, 0, new Font("Arial", Font.BOLD, 16), Color.BLUE));
        leftPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("Doctor ID:", JLabel.RIGHT);
        idLabel.setForeground(Color.GREEN);
        leftPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        leftPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nameLabel = new JLabel("Name:", JLabel.RIGHT);
        nameLabel.setForeground(Color.GREEN);
        leftPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        leftPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel ageLabel = new JLabel("Age:", JLabel.RIGHT);
        ageLabel.setForeground(Color.GREEN);
        leftPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        ageField = new JTextField(15);
        leftPanel.add(ageField, gbc);

        // Gender selection
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel genderLabel = new JLabel("Gender:", JLabel.RIGHT);
        genderLabel.setForeground(Color.GREEN);
        leftPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.setOpaque(false);
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        maleButton.setOpaque(false);
        femaleButton.setOpaque(false);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        leftPanel.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel specializationLabel = new JLabel("Specialization:", JLabel.RIGHT);
        specializationLabel.setForeground(Color.GREEN);
        leftPanel.add(specializationLabel, gbc);
        gbc.gridx = 1;
        specializationField = new JTextField(15);
        leftPanel.add(specializationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel experienceLabel = new JLabel("Experience:", JLabel.RIGHT);
        experienceLabel.setForeground(Color.GREEN);
        leftPanel.add(experienceLabel, gbc);
        gbc.gridx = 1;
        experienceField = new JTextField(15);
        leftPanel.add(experienceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel languageLabel = new JLabel("Languages:", JLabel.RIGHT);
        languageLabel.setForeground(Color.GREEN);
        leftPanel.add(languageLabel, gbc);
        gbc.gridx = 1;
        languageField = new JTextField(15);
        leftPanel.add(languageField, gbc);

        // Right panel for contact and schedule
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBounds(350, 30, 320, 400);
        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Contact & Schedule", 0, 0, new Font("Arial", Font.BOLD, 16), Color.RED));
        rightPanel.setOpaque(false);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel mobileLabel = new JLabel("Mobile No.:", JLabel.RIGHT);
        mobileLabel.setForeground(Color.GREEN);
        rightPanel.add(mobileLabel, gbc);
        gbc.gridx = 1;
        mobileField = new JTextField(15);
        rightPanel.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
        emailLabel.setForeground(Color.GREEN);
        rightPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        rightPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel mondayLabel = new JLabel("Monday:", JLabel.RIGHT);
        mondayLabel.setForeground(Color.GREEN);
        rightPanel.add(mondayLabel, gbc);
        gbc.gridx = 1;
        mondayField = new JTextField(15);
        rightPanel.add(mondayField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel wednesdayLabel = new JLabel("Wednesday:", JLabel.RIGHT);
        wednesdayLabel.setForeground(Color.GREEN);
        rightPanel.add(wednesdayLabel, gbc);
        gbc.gridx = 1;
        wednesdayField = new JTextField(15);
        rightPanel.add(wednesdayField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel fridayLabel = new JLabel("Friday:", JLabel.RIGHT);
        fridayLabel.setForeground(Color.GREEN);
        rightPanel.add(fridayLabel, gbc);
        gbc.gridx = 1;
        fridayField = new JTextField(15);
        rightPanel.add(fridayField, gbc);

        // Image upload section
        JLabel imageSectionLabel = new JLabel("Doctor Image:");
        imageSectionLabel.setForeground(Color.GREEN);
        imageSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        imageSectionLabel.setBounds(50, 450, 150, 30);
        backgroundLabel.add(imageSectionLabel);

        imageLabel = new JLabel();
        imageLabel.setBounds(150, 450, 200, 150);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        backgroundLabel.add(imageLabel);

        uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(50, 620, 150, 30);
        uploadButton.setBackground(Color.GREEN);
        uploadButton.addActionListener(this);
        backgroundLabel.add(uploadButton);

        BackButton = new JButton("Back");
        BackButton.setBounds(450, 670, 150, 30);
        BackButton.setBackground(Color.GREEN);
        BackButton.addActionListener(this);
        backgroundLabel.add(BackButton);

        // Add Doctor Button
        addButton = new JButton("Add Doctor");
        addButton.setBounds(50, 670, 150, 30);
        addButton.setBackground(Color.GREEN);
        addButton.addActionListener(this);
        backgroundLabel.add(addButton);

        // Delete Doctor Button
        deleteButton = new JButton("Delete Doctor");
        deleteButton.setBounds(250, 670, 150, 30);
        deleteButton.setBackground(Color.GREEN);
        deleteButton.addActionListener(this);
        backgroundLabel.add(deleteButton);

        // Add panels to background
        backgroundLabel.add(leftPanel);
        backgroundLabel.add(rightPanel);

        // Frame settings
        setSize(800, 750);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Doctor Image");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePath = selectedFile.getAbsolutePath();
                imageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
            }
        } else if (e.getSource() == addButton) {
            String doctorId = idField.getText();
            String name = nameField.getText();
            String age = ageField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String specialization = specializationField.getText();
            String experience = experienceField.getText().trim();
            String languages = languageField.getText();
            String mobile = mobileField.getText();
            String email = emailField.getText();
            String mondaySchedule = mondayField.getText();
            String wednesdaySchedule = wednesdayField.getText();
            String fridaySchedule = fridayField.getText();

            // Validate experience input
            if (!experience.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric value for experience.");
                return;
            }

            if (doctorId.isEmpty() || name.isEmpty() || specialization.isEmpty() || age.isEmpty() || gender.isEmpty() || experience.isEmpty() || languages.isEmpty() || mobile.isEmpty() || email.isEmpty() || imagePath == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields and upload an image.");
                return;
            }

            // Generate username and password
            String username = name.toLowerCase().replaceAll(" ", "") + doctorId;
            String password = generateRandomPassword(8); // Generating an 8-character password

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO doctors (doctor_id, name, age, gender, specialization, experience, languages, mobile, email, monday_schedule, wednesday_schedule, friday_schedule, image_path, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, doctorId);
                ps.setString(2, name);
                ps.setString(3, age);
                ps.setString(4, gender);
                ps.setString(5, specialization);
                ps.setInt(6, Integer.parseInt(experience));
                ps.setString(7, languages);
                ps.setString(8, mobile);
                ps.setString(9, email);
                ps.setString(10, mondaySchedule);
                ps.setString(11, wednesdaySchedule);
                ps.setString(12, fridaySchedule);
                ps.setString(13, imagePath);
                ps.setString(14, username);
                ps.setString(15, password);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Doctor added successfully!\nUsername: " + username + "\nPassword: " + password);
                ps.close();
                conn.c.close();

                this.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in database operation.");
            }
            
        } else if (e.getSource() == deleteButton) {
            String doctorId = idField.getText();

            if (doctorId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Doctor ID to delete.");
                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this doctor?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    Conn conn = new Conn();
                    String query = "DELETE FROM doctors WHERE doctor_id = ?";
                    PreparedStatement ps = conn.c.prepareStatement(query);
                    ps.setString(1, doctorId);

                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Doctor deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Doctor not found.");
                    }

                    ps.close();
                    conn.c.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error in database operation.");
                }
            }
        } else if(e.getSource() == BackButton) {
            setVisible(false);
            new AdminDashboard().setVisible(true);
        }
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        new AddDoctor();
    }
}
