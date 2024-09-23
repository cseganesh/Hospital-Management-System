package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientLogin extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, backButton;

    public PatientLogin() {
        setTitle("Patient Login");
        setLayout(null);

        // Background image setup
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 500, 300);
        add(backgroundLabel);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setForeground(Color.BLUE);
        usernameLabel.setBounds(80, 80, 100, 25);
        backgroundLabel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 80, 200, 25);
        backgroundLabel.add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(Color.BLUE);
        passwordLabel.setBounds(80, 120, 100, 25);
        backgroundLabel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 120, 200, 25);
        backgroundLabel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(200, 180, 100, 30);
        loginButton.setBackground(new Color(30, 144, 255));  // Dodger Blue
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(this);
        backgroundLabel.add(loginButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(80, 180, 100, 30);
        backButton.setBackground(new Color(220, 20, 60));  // Crimson Red
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

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

            // Authenticate the user
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM patients WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = conn.c.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("id");
                    JOptionPane.showMessageDialog(this, "Login successful! Welcome " + rs.getString("name") + "!");
                    this.setVisible(false);
                    new PatientDashboard(id);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }

                rs.close();
                preparedStatement.close();
                conn.c.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error in database operation.");
            }
        } else if (ae.getSource() == backButton) {
            this.setVisible(false);
            new HomePage(); // Assuming a HomePage class exists
        }
    }

    public static void main(String[] args) {
        new PatientLogin();
    }
}
