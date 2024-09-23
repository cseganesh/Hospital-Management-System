package hospital_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Payment extends JFrame implements ActionListener {
    JTextField cardNumberField, cardHolderNameField, expiryDateField, cvvField, amountField;
    JButton payButton, cancelButton, backButton;
    int appointmentId;

    public Payment(int appointmentId, double amount) {
        this.appointmentId = appointmentId;

        setTitle("Online Payment");
        setLayout(null);

        // Setting up the background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/2.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, 500, 400);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Labels and text fields for card details
        JLabel titleLabel = new JLabel("Payment Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 20, 200, 30);
        titleLabel.setForeground(Color.GREEN);
        backgroundLabel.add(titleLabel);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setBounds(50, 80, 150, 25);
        cardNumberLabel.setForeground(Color.GREEN);
        backgroundLabel.add(cardNumberLabel);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(200, 80, 200, 25);
        backgroundLabel.add(cardNumberField);

        JLabel cardHolderNameLabel = new JLabel("Card Holder Name:");
        cardHolderNameLabel.setBounds(50, 120, 150, 25);
        cardHolderNameLabel.setForeground(Color.GREEN);
        backgroundLabel.add(cardHolderNameLabel);

        cardHolderNameField = new JTextField();
        cardHolderNameField.setBounds(200, 120, 200, 25);
        backgroundLabel.add(cardHolderNameField);

        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        expiryDateLabel.setBounds(50, 160, 150, 25);
        expiryDateLabel.setForeground(Color.GREEN);
        backgroundLabel.add(expiryDateLabel);

        expiryDateField = new JTextField();
        expiryDateField.setBounds(200, 160, 100, 25);
        backgroundLabel.add(expiryDateField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setBounds(50, 200, 150, 25);
        cvvLabel.setForeground(Color.GREEN);
        backgroundLabel.add(cvvLabel);

        cvvField = new JTextField();
        cvvField.setBounds(200, 200, 100, 25);
        backgroundLabel.add(cvvField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 240, 150, 25);
        amountLabel.setForeground(Color.GREEN);
        backgroundLabel.add(amountLabel);

        amountField = new JTextField(String.valueOf(amount));
        amountField.setBounds(200, 240, 100, 25);
        amountField.setEditable(false);
        backgroundLabel.add(amountField);

        // Pay Button
        payButton = new JButton("Pay Now");
        payButton.setBounds(80, 300, 100, 30);
        payButton.setBackground(new Color(34, 139, 34));
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(this);
        backgroundLabel.add(payButton);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 300, 100, 30);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        backgroundLabel.add(cancelButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(320, 300, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            dispose(); // Close the Payment window
            new AppointmentBooking(); // Open AppointmentBooking window
        });
        backgroundLabel.add(backButton);

        // Frame settings
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

  @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == payButton) {
        String cardNumber = cardNumberField.getText().trim();
        String cardHolderName = cardHolderNameField.getText().trim();
        String expiryDate = expiryDateField.getText().trim();
        String cvv = cvvField.getText().trim();
        double amount = Double.parseDouble(amountField.getText());

        // Basic validation
        if (cardNumber.isEmpty() || cardHolderName.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save payment details to the database
        try (Conn conn = new Conn()) {
            String query = "INSERT INTO payments (card_number, card_holder_name, expiry_date, cvv, amount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.c.prepareStatement(query)) {
                stmt.setString(1, cardNumber);
                stmt.setString(2, cardHolderName);
                stmt.setString(3, expiryDate);
                stmt.setString(4, cvv);
                stmt.setDouble(5, amount); // Ensure this is the last one

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the payment window
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Payment failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    } else if (e.getSource() == cancelButton) {
        dispose(); // Close the Payment window
    }
}

    public static void main(String[] args) {
        new Payment(1, 1000.0); // Test with a sample appointment ID and amount
    }
}
