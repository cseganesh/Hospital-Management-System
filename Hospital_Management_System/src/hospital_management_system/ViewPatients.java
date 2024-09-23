package hospital_management_system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ViewPatients extends JFrame implements ActionListener {

    JTable table;
    JButton backButton, exitButton;
    String[] columns = {"Patient ID", "Name", "DOB", "Gender", "Blood Group", "Email", "Mobile", "Address", "Private/CGHS"};
    String[][] data;

    public ViewPatients() {
        // Frame settings
        setTitle("View Patients");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255));  // Light blue background

        // Fetch data from the database
        try {
            Conn conn = new Conn();
            String query = "SELECT id, name, dob, gender, blood_group, email, mobile, address, private_cghs FROM patients";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Use ArrayList to hold rows dynamically
            ArrayList<String[]> dataList = new ArrayList<>();

            // Populate data
            while (rs.next()) {
                String[] row = new String[9];
                row[0] = rs.getString("id");             // Patient ID
                row[1] = rs.getString("name");           // Name
                row[2] = rs.getString("dob");            // Date of Birth (DOB)
                row[3] = rs.getString("gender");         // Gender
                row[4] = rs.getString("blood_group");    // Blood Group
                row[5] = rs.getString("email");          // Email
                row[6] = rs.getString("mobile");         // Mobile
                row[7] = rs.getString("address");        // Address
                row[8] = rs.getString("private_cghs");   // Private/CGHS status
                dataList.add(row);
            }

            // Convert ArrayList to a 2D array
            data = new String[dataList.size()][9];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

            // Close connections
            rs.close();
            ps.close();
            conn.c.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from the database.");
        }

        // Create JTable
        table = new JTable(data, columns);
        table.setFont(new Font("Arial", Font.PLAIN, 14));  // Set table font
        table.setRowHeight(30);  // Increase row height
        table.setGridColor(Color.LIGHT_GRAY);

        // Customize header
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));  // Steel blue header background
        header.setForeground(Color.WHITE);  // White text color for header
        header.setFont(new Font("Arial", Font.BOLD, 16));  // Font for header

        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    component.setBackground(new Color(224, 255, 255));  // Light cyan for even rows
                } else {
                    component.setBackground(Color.WHITE);  // White for odd rows
                }
                return component;
            }
        });

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));  // Match background color with frame

        // Back button styling
        backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(this);  // Add action listener
        buttonPanel.add(backButton);

        // Exit button styling
        exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(this);  // Add action listener
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(1000, 500);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));  // Steel blue background
        button.setForeground(Color.WHITE);  // White text
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Add padding

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));  // Lighter blue when hovered
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  // Original color when not hovered
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new AdminDashboard().setVisible(true);
        } else if (ae.getSource() == exitButton) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new ViewPatients();
    }
}
