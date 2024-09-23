package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {
    JButton loginButton, registerButton;

    // Constructor for the HomePage
    public HomePage() {
        // Frame settings
        setTitle("Hospital Management System");
        setLayout(null);

        // Load and scale the background image to cover the full frame size (800x500)
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpeg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH); // Scale to frame size
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);

        // JLabel to hold the full-sized background image
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 800, 500); // Full window size
        add(backgroundLabel);

        // Create the Login button with styling
        loginButton = createStyledButton("Login", new Color(30, 144, 255)); // Dodger Blue
        loginButton.setBounds(480, 400, 130, 40); // Positioned at the bottom right
        loginButton.addActionListener(this); // Add action listener for the button
        backgroundLabel.add(loginButton); // Add button to backgroundLabel

        // Create Register button with styling
        registerButton = createStyledButton("New Register", new Color(34, 139, 34)); // Forest Green
        registerButton.setBounds(630, 400, 130, 40); // Positioned to the right of the Login button
        registerButton.addActionListener(this); // Add action listener for the button
        backgroundLabel.add(registerButton); // Add button to backgroundLabel

        // Ensure that components are added above the background image
        backgroundLabel.setLayout(null); // Setting layout for the backgroundLabel for proper positioning of buttons

        // Frame background and size
        getContentPane().setBackground(Color.WHITE);
        setSize(800, 500);
        setLocation(500, 200); // Position the window in the center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ac) {
        if (ac.getSource() == registerButton) {
            setVisible(false); // Close the current window
            new Register().setVisible(true); // Open the Register window (assumes you have a Register.java class)
        } else if (ac.getSource() == loginButton) {
            setVisible(false); // Close the current window
            new Login().setVisible(true); // Open the Login window
        }
    }

    // Method to create a styled button with rounded corners and color
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor); // Button background color
        button.setForeground(Color.WHITE); // Button text color
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Set button font style and size
        button.setFocusPainted(false); // Remove button focus border
        button.setOpaque(true);
        button.setContentAreaFilled(true); // Enable button background color
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set the cursor to a hand on hover

        return button;
    }

    public static void main(String[] args) {
        new HomePage(); // Show the home page
    }
}
