package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JButton AloginButton, DLoginButton, PLoginButton, Hospital, backButton, exitButton; // Added new buttons

    // Constructor for the Login form
    public Login() {
        // Frame settings
        setTitle("SLMCH Hospital");
        setLayout(null);

        // Load and scale the background image to cover the full frame size (800x500)
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);

        // JLabel to hold the full-sized background image
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 800, 500);
        add(backgroundLabel);
        
        // Create Hospital Label Button (for consistent styling)
        Hospital = createStyledButton("SLMCH Hospital", new Color(100, 155, 255));
        Hospital.setBounds(250, 20, 300, 60); 
        backgroundLabel.add(Hospital); 

        // Create Admin Login button (left side)
        AloginButton = createStyledButton("ADMIN LOGIN", new Color(100, 155, 255));
        AloginButton.setBounds(80, 300, 200, 40);
        backgroundLabel.add(AloginButton); 

        // Create Doctor Login button (middle)
        DLoginButton = createStyledButton("DOCTOR LOGIN", new Color(100, 155, 255));
        DLoginButton.setBounds(300, 350, 200, 40);
        backgroundLabel.add(DLoginButton); 

        // Create Patient Login button (right side)
        PLoginButton = createStyledButton("PATIENT LOGIN", new Color(100, 155, 255));
        PLoginButton.setBounds(520, 300, 200, 40);
        backgroundLabel.add(PLoginButton); 

        // Create Back Button
        backButton = createStyledButton("BACK", new Color(100, 155, 255));
        backButton.setBounds(80, 350, 200, 40);
        backgroundLabel.add(backButton);

        // Create Exit Button
        exitButton = createStyledButton("EXIT", new Color(220, 20, 60)); // Different color for exit button
        exitButton.setBounds(520, 350, 200, 40);
        backgroundLabel.add(exitButton);

        // Ensure that components are added above the background image
        backgroundLabel.setLayout(null);

        // Add ActionListener for the buttons
        AloginButton.addActionListener(this);
        DLoginButton.addActionListener(this);
        PLoginButton.addActionListener(this);
        backButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Frame settings
        getContentPane().setBackground(Color.WHITE);
        setSize(800, 500);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the frame
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == AloginButton) {
            setVisible(false);
            new AdminLogin().setVisible(true);
        } else if (ae.getSource() == DLoginButton) {
            setVisible(false);
            new DoctorLogin().setVisible(true);
        } else if (ae.getSource() == PLoginButton) {
            setVisible(false);
            new PatientLogin().setVisible(true);
        } else if (ae.getSource() == backButton) {
            // Open HomePage when the Back button is clicked
            setVisible(false);
            new HomePage().setVisible(true); // Assuming you have a HomePage class
        } else if (ae.getSource() == exitButton) {
            // Exit the application
            System.exit(0);
        }
    }

    // Method to create a styled button with rounded corners and color
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor); 
        button.setForeground(Color.WHITE); 
        button.setFont(new Font("Arial", Font.BOLD, 16)); 
        button.setFocusPainted(false); 
        button.setOpaque(true);
        button.setContentAreaFilled(true); 
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); 
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

        return button;
    }

    public static void main(String[] args) {
        // Show the login form
        new Login();
    }
}
