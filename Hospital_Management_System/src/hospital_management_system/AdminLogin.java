package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, backButton; // Added backButton

    // Constructor for Admin Login form
    public AdminLogin() {
        // Frame settings
        setTitle("Admin Login");
        setLayout(null);

        // Load and scale the background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);

        // JLabel to hold the full-sized background image
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 500, 300);
        add(backgroundLabel);

        // Create Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setForeground(Color.BLUE);
        usernameLabel.setBounds(80, 80, 100, 25);
        backgroundLabel.add(usernameLabel);

        // Create Username TextField
        usernameField = new JTextField();
        usernameField.setBounds(180, 80, 200, 25);
        backgroundLabel.add(usernameField);

        // Create Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(Color.BLUE);
        passwordLabel.setBounds(80, 120, 100, 25);
        backgroundLabel.add(passwordLabel);

        // Create Password TextField
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 120, 200, 25);
        backgroundLabel.add(passwordField);

        // Create Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(200, 180, 100, 30);
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.BLUE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(this);
        backgroundLabel.add(loginButton);

        // Create Back Button
        backButton = new JButton("Back");
        backButton.setBounds(80, 180, 100, 30);
        backButton.setBackground(new Color(220, 20, 60)); // Crimson Red
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        // Frame settings
        setSize(500, 300);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.");
                return;
            }

            // Authenticate with the database
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = conn.c.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful! Welcome, gagan " + rs.getString("name") + "!");
                    this.setVisible(false);
                    new AdminDashboard(); // Uncomment if you have an AdminDashboard class
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }

                // Close resources
                rs.close();
                preparedStatement.close();
                conn.c.close();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in database operation");
            }
        } else if (ae.getSource() == backButton) {
            // Hide the current AdminLogin window
            this.setVisible(false);

            // Open the Login window
            new Login();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
