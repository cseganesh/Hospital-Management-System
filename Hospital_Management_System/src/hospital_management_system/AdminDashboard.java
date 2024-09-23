package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame implements ActionListener {
    JButton viewPatients, viewDoctors, addDoctor, exitButton, backButton; // Added Back button

    public AdminDashboard() {
        // Frame settings
        setTitle("Admin Dashboard");
        setLayout(null);

        // Load and scale the background image to cover the full frame size (600x400)
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/4.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);

        // JLabel to hold the full-sized background image
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 600, 400);
        add(backgroundLabel); // Add background label to the frame

        // Set the layout of the background label to null for custom positioning
        backgroundLabel.setLayout(null);

        // Title label
        JLabel title = new JLabel("Admin Dashboard", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(150, 30, 300, 30);
        title.setForeground(Color.GREEN); // Set title text color to contrast with background
        backgroundLabel.add(title); // Add title label to backgroundLabel

        // View Patients Button
        viewPatients = new JButton("View Patients");
        viewPatients.setBounds(200, 100, 200, 30);
        viewPatients.setBackground(new Color(34, 139, 34)); // Dark green background
        viewPatients.setForeground(Color.WHITE); // White text
        viewPatients.setFont(new Font("Arial", Font.BOLD, 14)); // Font styling
        viewPatients.addActionListener(this);
        backgroundLabel.add(viewPatients); // Add to backgroundLabel

        // View Doctors Button
        viewDoctors = new JButton("View Doctors");
        viewDoctors.setBounds(200, 150, 200, 30);
        viewDoctors.setBackground(new Color(70, 130, 180)); // Steel blue background
        viewDoctors.setForeground(Color.WHITE); // White text
        viewDoctors.setFont(new Font("Arial", Font.BOLD, 14)); // Font styling
        viewDoctors.addActionListener(this);
        backgroundLabel.add(viewDoctors); // Add to backgroundLabel

        // Add Doctor Button
        addDoctor = new JButton("Add Doctor");
        addDoctor.setBounds(200, 200, 200, 30);
        addDoctor.setBackground(new Color(255, 140, 0)); // Dark orange background
        addDoctor.setForeground(Color.WHITE); // White text
        addDoctor.setFont(new Font("Arial", Font.BOLD, 14)); // Font styling
        addDoctor.addActionListener(this);
        backgroundLabel.add(addDoctor); // Add to backgroundLabel

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(200, 300, 200, 30);
        exitButton.setBackground(Color.RED); // Red background for exit
        exitButton.setForeground(Color.WHITE); // White text
        exitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font styling
        exitButton.addActionListener(this);
        backgroundLabel.add(exitButton); // Add to backgroundLabel

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(200, 250, 200, 30);
        backButton.setBackground(new Color(100, 155, 255)); // Light blue background
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font styling
        backButton.addActionListener(this);
        backgroundLabel.add(backButton); // Add to backgroundLabel

        // Frame settings
        setSize(600, 400);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == viewPatients) {
            // Open view patients window
            setVisible(false);
            new ViewPatients().setVisible(true);
        } else if (ae.getSource() == viewDoctors) {
            // Open view doctors window
            setVisible(false);
            new ViewDoctors().setVisible(true);
        } else if (ae.getSource() == addDoctor) {
            // Open add doctor window
            setVisible(false);
            new AddDoctor().setVisible(true);
        } else if (ae.getSource() == exitButton) {
            // Exit the application
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (ae.getSource() == backButton) {
            // Open AdminLogin window when the Back button is clicked
            setVisible(false);
            new AdminLogin().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
