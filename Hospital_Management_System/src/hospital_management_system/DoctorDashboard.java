package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DoctorDashboard extends JFrame implements ActionListener {

    JLabel nameLabel, ageLabel, genderLabel, specializationLabel, experienceLabel, languageLabel, mobileLabel, emailLabel, imageLabel;
    JLabel mondayScheduleLabel, wednesdayScheduleLabel, fridayScheduleLabel;
    JButton AddButton, removeButton, backButton;
    String doctorId;  // Store doctorId for later use

    public DoctorDashboard(String doctorId) {
        this.doctorId = doctorId;  // Save doctor ID
        setTitle("Doctor Details");
        setLayout(null);

        JPanel cardPanel = new JPanel();
        cardPanel.setBounds(20, 80, 600, 550); // Increased size
        cardPanel.setBorder(BorderFactory.createTitledBorder("Doctor Information"));
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.LIGHT_GRAY);  // Set background color for card panel
        add(cardPanel);

        // Doctor Image
        imageLabel = new JLabel();
        imageLabel.setBounds(420, 20, 120, 150); // Adjusted position for the larger panel
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardPanel.add(imageLabel);

        // Doctor Details
        nameLabel = new JLabel();
        nameLabel.setBounds(20, 20, 250, 30);
        cardPanel.add(nameLabel);

        ageLabel = new JLabel();
        ageLabel.setBounds(20, 60, 250, 30);
        cardPanel.add(ageLabel);

        genderLabel = new JLabel();
        genderLabel.setBounds(20, 100, 250, 30);
        cardPanel.add(genderLabel);

        specializationLabel = new JLabel();
        specializationLabel.setBounds(20, 140, 250, 30);
        cardPanel.add(specializationLabel);

        experienceLabel = new JLabel();
        experienceLabel.setBounds(20, 180, 250, 30);
        cardPanel.add(experienceLabel);

        languageLabel = new JLabel();
        languageLabel.setBounds(20, 220, 250, 30);
        cardPanel.add(languageLabel);

        mobileLabel = new JLabel();
        mobileLabel.setBounds(20, 290, 250, 30);
        cardPanel.add(mobileLabel);

        emailLabel = new JLabel();
        emailLabel.setBounds(20, 320, 250, 30);
        cardPanel.add(emailLabel);

        mondayScheduleLabel = new JLabel();
        mondayScheduleLabel.setBounds(20, 390, 250, 30);
        cardPanel.add(mondayScheduleLabel);

        wednesdayScheduleLabel = new JLabel();
        wednesdayScheduleLabel.setBounds(20, 420, 250, 30);
        cardPanel.add(wednesdayScheduleLabel);

        fridayScheduleLabel = new JLabel();
        fridayScheduleLabel.setBounds(20, 450, 250, 30);
        cardPanel.add(fridayScheduleLabel);

        // Buttons
        AddButton = new JButton("Add Doctor");
        AddButton.setBounds(20, 500, 150, 30); // Adjusted position
        AddButton.setBackground(new Color(102, 205, 170));  // Light Sea Green
        AddButton.addActionListener(this);
        cardPanel.add(AddButton);

        removeButton = new JButton("Remove Doctor");
        removeButton.setBounds(180, 500, 150, 30); // Adjusted position
        removeButton.setBackground(new Color(220, 20, 60));  // Crimson Red
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(this);
        cardPanel.add(removeButton);

        backButton = new JButton("Back");
        backButton.setBounds(340, 500, 150, 30); // Adjusted position
        backButton.setBackground(new Color(30, 144, 255));  // Dodger Blue
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        cardPanel.add(backButton);

        setSize(700, 700); // Increased frame size
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Load the doctor data based on the ID passed from the login
        loadDoctorData(doctorId);
    }

    private void loadDoctorData(String doctorId) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM doctors WHERE doctor_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, doctorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nameLabel.setText("Name: " + rs.getString("name"));
                ageLabel.setText("Age: " + rs.getString("age"));
                genderLabel.setText("Gender: " + rs.getString("gender"));
                specializationLabel.setText("Specialization: " + rs.getString("specialization"));
                experienceLabel.setText("Experience: " + rs.getString("experience") + " years");
                languageLabel.setText("Languages: " + rs.getString("languages"));
                mobileLabel.setText("Mobile: " + rs.getString("mobile"));
                emailLabel.setText("Email: " + rs.getString("email"));
                mondayScheduleLabel.setText("Monday: " + rs.getString("monday_schedule"));
                wednesdayScheduleLabel.setText("Wednesday: " + rs.getString("wednesday_schedule"));
                fridayScheduleLabel.setText("Friday: " + rs.getString("friday_schedule"));

                String imagePath = rs.getString("image_path");
                if (imagePath != null) {
                    imageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH)));
                } else {
                    imageLabel.setIcon(null);
                }
            }

            rs.close();
            ps.close();
            conn.c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching doctor data.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            // Return to DoctorLogin screen
            this.setVisible(false);
            new DoctorLogin();
            
        } else if (ae.getSource() == AddButton) {
            // Open edit form (you can create a new EditDoctorForm for this purpose)
            setVisible(false);
            new AddDoctor().setVisible(true);
            // You can open a new EditDoctorForm window here if needed  
            
            
            
        } else if (ae.getSource() == removeButton) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this doctor?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                removeDoctor();
            }
        }
    }

    private void removeDoctor() {
        try {
            Conn conn = new Conn();
            String query = "DELETE FROM doctors WHERE doctor_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, doctorId);
            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Doctor removed successfully.");
                this.setVisible(false);
                new DoctorLogin();  // Return to DoctorLogin after removal
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove doctor.");
            }

            ps.close();
            conn.c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error removing doctor.");
        }
    }

    public static void main(String[] args) {
        new DoctorDashboard("1"); // Test with a sample doctor ID
    }
}
