package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDashboard extends JFrame implements ActionListener {

    JLabel nameLabel, dobLabel, genderLabel, bloodGroupLabel, emailLabel, mobileLabel, addressLabel, privateCghsLabel, imageLabel;
    JButton addButton, removeButton, backButton;
    String id;  // Store patient ID

    public PatientDashboard(String id) {
        this.id = id;  // Store patient ID
        setTitle("Patient Details");
        setLayout(null);

        // Create a panel for patient details
        JPanel cardPanel = new JPanel();
        cardPanel.setBounds(20, 80, 600, 550);
        cardPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.LIGHT_GRAY);
        add(cardPanel);

        // Patient Image
        imageLabel = new JLabel();
        imageLabel.setBounds(420, 20, 120, 150);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardPanel.add(imageLabel);

        // Patient Details
        nameLabel = new JLabel();
        nameLabel.setBounds(20, 20, 250, 30);
        cardPanel.add(nameLabel);

        dobLabel = new JLabel();
        dobLabel.setBounds(20, 60, 250, 30);
        cardPanel.add(dobLabel);

        genderLabel = new JLabel();
        genderLabel.setBounds(20, 100, 250, 30);
        cardPanel.add(genderLabel);

        bloodGroupLabel = new JLabel();
        bloodGroupLabel.setBounds(20, 140, 250, 30);
        cardPanel.add(bloodGroupLabel);

        addressLabel = new JLabel();
        addressLabel.setBounds(20, 180, 250, 30);
        cardPanel.add(addressLabel);

        mobileLabel = new JLabel();
        mobileLabel.setBounds(20, 290, 250, 30);
        cardPanel.add(mobileLabel);

        emailLabel = new JLabel();
        emailLabel.setBounds(20, 320, 250, 30);
        cardPanel.add(emailLabel);

        privateCghsLabel = new JLabel();
        privateCghsLabel.setBounds(20, 420, 250, 30);
        cardPanel.add(privateCghsLabel);

        // Buttons
        addButton = new JButton("Add Patient");
        addButton.setBounds(20, 500, 150, 30);
        addButton.setBackground(new Color(102, 205, 170));  // Light Sea Green
        addButton.addActionListener(this);
        cardPanel.add(addButton);

        removeButton = new JButton("Remove Patient");
        removeButton.setBounds(180, 500, 150, 30);
        removeButton.setBackground(new Color(220, 20, 60));  // Crimson Red
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(this);
        cardPanel.add(removeButton);

        backButton = new JButton("Back");
        backButton.setBounds(340, 500, 150, 30);
        backButton.setBackground(new Color(30, 144, 255));  // Dodger Blue
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        cardPanel.add(backButton);

        setSize(700, 700);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Load patient data based on the provided ID
        loadPatientData(id);
    }

    private void loadPatientData(String patientId) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM patients WHERE id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, patientId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nameLabel.setText("Name: " + rs.getString("name"));
                dobLabel.setText("DOB: " + rs.getString("dob"));
                genderLabel.setText("Gender: " + rs.getString("gender"));
                bloodGroupLabel.setText("Blood Group: " + rs.getString("blood_group"));
                emailLabel.setText("Email: " + rs.getString("email"));
                mobileLabel.setText("Mobile: " + rs.getString("mobile"));
                addressLabel.setText("Address: " + rs.getString("address"));
                privateCghsLabel.setText("Private/CGHS: " + rs.getString("private_cghs"));

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
            JOptionPane.showMessageDialog(this, "Error fetching patient data.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            this.setVisible(false);
            new PatientLogin(); // Return to PatientLogin screen
        } else if (ae.getSource() == addButton) {
            setVisible(false);
            new Register().setVisible(true); // Open patient registration form
        } else if (ae.getSource() == removeButton) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this patient?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                removePatient();
            }
        }
    }

    private void removePatient() {
        try {
            Conn conn = new Conn();
            String query = "DELETE FROM patients WHERE id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, id);
            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Patient removed successfully.");
                this.setVisible(false);
                new PatientLogin();  // Return to PatientLogin after removal
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove patient.");
            }

            ps.close();
            conn.c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error removing patient.");
        }
    }

    public static void main(String[] args) {
        new PatientDashboard("1"); // Test with a sample patient ID
    }
}
